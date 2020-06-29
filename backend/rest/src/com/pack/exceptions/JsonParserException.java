package com.pack.exceptions;

public class JsonParserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JsonParserException() {
        super();
    }

    public JsonParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonParserException(Throwable cause) {
        super(cause);
    }

}