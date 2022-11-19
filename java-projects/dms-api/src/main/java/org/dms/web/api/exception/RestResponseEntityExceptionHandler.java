package org.dms.web.api.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { DmsApiException.class })
	protected ResponseEntity<?> handleDmsApiException(DmsApiException ex) {
		String message = "API Error: " + ex.getLocalizedMessage();
		log.warn("API exception occured.", message);
		return new ResponseEntity<ExceptionData>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<?> handleException(Exception ex) {
		String message = "Server Error: " + ex.getLocalizedMessage();
		log.warn("Internal exception occured.", message);
		return new ResponseEntity<ExceptionData>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Data
	@AllArgsConstructor
	public class ExceptionData {

		private String message;

	}

}
