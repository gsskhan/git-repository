package org.dms.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DmsRESTExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { DmsException.class })
	protected ResponseEntity<Exception> handleDmsException(DmsException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleAllExceptions(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Document Management System encountered internal error. Please contact support.";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

}
