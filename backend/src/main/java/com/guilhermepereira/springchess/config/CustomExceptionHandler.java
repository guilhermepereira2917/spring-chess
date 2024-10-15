package com.guilhermepereira.springchess.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception, WebRequest webRequest) {
		String message = String.format("An internal error has occurred on the server.\nException: %s", exception.getMessage());

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
