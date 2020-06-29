package com.pack.utils.exception;

import java.util.ArrayList;
import java.util.List;

import com.pack.pojo.Erro;


public class CustomValidationException extends Exception {
	private static final long serialVersionUID = -6127522337621022725L;

	private List<Erro> messages = new ArrayList<>();

	public CustomValidationException() {
		super();
	}

	public CustomValidationException(List<Erro> messages) {
		super();
		this.messages = messages;
	}

	public CustomValidationException(Erro message) {
		super();
		this.messages.add(message);
	}

	public CustomValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomValidationException(String message) {
		super(message);
	}

	public CustomValidationException(Throwable cause) {
		super(cause);
	}

	public List<Erro> getMessages() {
		return this.messages;
	}

}