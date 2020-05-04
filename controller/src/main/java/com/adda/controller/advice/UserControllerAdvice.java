package com.adda.controller.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adda.DemoApp;
import com.adda.exception.InvalidUserException;

@ControllerAdvice(basePackageClasses = {DemoApp.class})
public class UserControllerAdvice {

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserException(
			final InvalidUserException invalidUserException, HttpServletRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", invalidUserException.getMessage());
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

}
