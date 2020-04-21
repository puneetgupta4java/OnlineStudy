package com.hsc.onlinestudy.exceptions;

public class StudentSqlException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	public StudentSqlException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
