package com.adda.controller.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adda.exception.InvalidTokenException;
import com.adda.exception.InvalidUserException;

@ControllerAdvice(basePackages = { "com.adda" })
public class UserControllerAdvice {

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<Map<String, String>> handleInvalidTokenException(
			final InvalidTokenException invalidTokenException, HttpServletRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", invalidTokenException.getMessage());
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserException(
			final InvalidUserException invalidUserException, HttpServletRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", invalidUserException.getMessage());
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

}
