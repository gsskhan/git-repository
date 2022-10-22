package com.demo.javers.exceptions;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ApiException.class)
	public ResponseEntity<ErrorMessage> handleApiException(ApiException apiEx) {
		String message = Optional.ofNullable(apiEx.getMessage()).orElse("");
		HttpStatus httpStatus = Optional.ofNullable(apiEx.getHttpStatus()).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
		ErrorMessage errorMessage = new ErrorMessage(1000, message);
		log.warn("Global Api Exception Handler Returned [HttpStatus: {}, ErrorMessage: {}]", httpStatus, errorMessage);
		return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleAllException(Exception ex) {
		String message = Optional.ofNullable(ex.getLocalizedMessage()).orElse("");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorMessage errorMessage = new ErrorMessage(9999, message);
		log.warn("Global All Exception Handler Returned [HttpStatus: {}, ErrorMessage: {}]", httpStatus, errorMessage);
		return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
	}

}
