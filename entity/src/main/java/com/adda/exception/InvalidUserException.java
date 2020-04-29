package com.adda.exception;

public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidUserException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
