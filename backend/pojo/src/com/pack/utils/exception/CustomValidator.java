package com.pack.utils.exception;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pack.pojo.Erro;
//import com.pack.domain.service.ErroService;
import com.pack.utils.exception.CustomException;
import com.pack.utils.exception.CustomValidationException;

@Named
public abstract class CustomValidator<T> {

	@Inject
	private Validator validator;

	protected List<com.pack.pojo.Erro> beanValidate(T object) {
		List<com.pack.pojo.Erro> erros = new ArrayList<>();
		
		for (ConstraintViolation<?> violation : validator.validate(object)) {
			String field = String.valueOf(this.getIteratorLastValue(violation.getPropertyPath().iterator()));

			try {
				
				if (violation.getLeafBean().getClass().getDeclaredField(field).isAnnotationPresent(JsonProperty.class)) {
					field = violation.getLeafBean().getClass().getDeclaredField(field).getDeclaredAnnotation(JsonProperty.class).value();
				}
				Class t = JsonProperty.class;
				if(violation.getLeafBean().getClass().getDeclaredField(field).isAnnotationPresent(t)) {
					
				}
			} catch (Exception e) {
				
			}

			com.pack.pojo.Erro erro = new com.pack.pojo.Erro();
			String[] split = violation.getMessage().split("#");
			if (split.length > 1) {
				erro.setCoRetorno(split[0]);
				erro.setDsRetorno(split[1]);
				erro.setDsField(field);
			} else {
				erro.setDsRetorno(split[0]);
			}

			erro.setDsRetorno(MessageFormat.format(erro.getDsRetorno(), field));
			erro.setDsValor(String.valueOf(violation.getInvalidValue()));
			erros.add(erro);
		}
		
		return erros;
	}

	public abstract void validate(T object) throws CustomValidationException;

	private <K> K getIteratorLastValue(Iterator<K> it) {
		K result = null;
		while (it.hasNext()) {
			result = it.next();
		}
		return result;
	}

}