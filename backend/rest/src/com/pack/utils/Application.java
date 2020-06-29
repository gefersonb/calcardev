package com.pack.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;


public class Application {

	private static final Logger LOG = Logger.getLogger(Application.class);

	private static final ResourceBundle APP_RESOURCE_BUNDLE;

	// Constantes das System Properties
	private static final String TOKEN_SECRET_KEY_PROPERTY = "desafio.token.secret-key";
	private static final String ACCESS_TOKEN_EXPIRATION_TIME_PROPERTY = "desafio.access-token-expiration-time";

	// Propriedades da arquitetura
	public static final String TOKEN_SECRET_KEY;
	public static final Integer ACCESS_TOKEN_EXPIRATION_TIME;
	
	static {
		APP_RESOURCE_BUNDLE = ResourceBundle.getBundle("app");

		TOKEN_SECRET_KEY = System.getProperty(TOKEN_SECRET_KEY_PROPERTY);
		ACCESS_TOKEN_EXPIRATION_TIME = Application.getPropertyAsInt(ACCESS_TOKEN_EXPIRATION_TIME_PROPERTY).orElse(300);
		
	}

	protected Application() {
	}

	public static final String getMessage(String key, Object... parameters) {
		String message = getMessage(key);
		MessageFormat formatter = new MessageFormat(message);
		return formatter.format(parameters);
	}

	private static final Optional<String> getProperty(String key) {
		String value = null;
		try {
			value = APP_RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			LOG.debug(e);
			value = System.getProperty(key);
		}
		Optional<String> property;
		if (StringUtils.isNullOrEmpty(value)) {
			property = Optional.empty();
		} else {
			property = Optional.of(value.trim());
		}
		return property;
	}

	public static final Optional<String> getPropertyAsString(String property) {
		return getProperty(property);
	}

	public static final Optional<Integer> getPropertyAsInt(String property) {
		Optional<Integer> result = Optional.empty();
		Optional<String> value = getPropertyAsString(property);
		if (value.isPresent()) {
			try {
				result = Optional.of(Integer.parseInt(value.get()));
			} catch (Exception e) {
				LOG.warn("Erro recuperar o valor da propridade " + property + ".", e);
			}
		}
		return result;
	}
	

}