package com.adda.controller;

import java.util.List;

public class ServiceResponse {

	private String message = null;
	private String error = null;

	private List<Object> resultSet;

	public ServiceResponse() {

	}

	public ServiceResponse(String errorString, String message) {
		setMessage(message);
		setError(errorString);
	}

	public void setMessage(String messageString) {
		message = (messageString != null) ? new String(messageString) : null;
	}

	public String getMessage() {
		return (message);
	}

	public String getError() {
		return (error);
	}

	public void setError(String errorString) {
		error = (errorString != null) ? new String(errorString) : null;
	}

	public static ServiceResponse createSuccessResponse(String message) {
		ServiceResponse response = new ServiceResponse(null, message);

		return (response);
	}

	public static ServiceResponse createFailureResponse(String error) {
		ServiceResponse response = new ServiceResponse(null, error);

		return (response);
	}

	public List<Object> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<Object> resultSet) {
		this.resultSet = resultSet;
	}

}
