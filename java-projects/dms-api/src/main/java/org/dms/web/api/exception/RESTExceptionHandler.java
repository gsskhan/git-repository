package org.dms.web.api.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

	// handle DmsApi exceptions
	@ExceptionHandler(value = { DmsApiException.class })
	protected ResponseEntity<ExceptionData> handleDmsApiException(DmsApiException ex) {
		String message = "API Error: " + ex.getLocalizedMessage();
		log.warn("API exception occured.", message);
		return new ResponseEntity<>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// handle DmsRuntimeException exceptions
	@ExceptionHandler(value = { DmsRuntimeException.class })
	protected ResponseEntity<ExceptionData> handleDmsRuntimeException(DmsRuntimeException ex) {
		String message = "Runtime Error: " + ex.getLocalizedMessage();
		log.warn("Runtime exception occured.", message);
		return new ResponseEntity<>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// handle all other exceptions
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ExceptionData> handleException(Exception ex) {
		String message = "Server Error: " + ex.getLocalizedMessage();
		log.warn("Internal exception occured.", message);
		return new ResponseEntity<>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Data
	@AllArgsConstructor
	public class ExceptionData {

		private String message;

	}

}
