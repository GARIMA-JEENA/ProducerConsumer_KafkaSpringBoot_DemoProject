package com.training.task.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.task.exception.UserInvalidInputException;
import com.training.task.exception.UserNotFoundException;

@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserInvalidInputException.class)
	public ResponseEntity<String> handleEmptyInputFunc(UserInvalidInputException emptyInputException) {
		String errorMessage = emptyInputException.getMessage();
		return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleIdNotFoundFunc(UserNotFoundException userNotFoundException) {
		String errorMessage = userNotFoundException.getMessage();
		return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Please Change Your Method Type ", HttpStatus.METHOD_NOT_ALLOWED);
	}

}
