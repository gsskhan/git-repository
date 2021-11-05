package org.dms.web.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = { DmsApiException.class })
	protected ResponseEntity<?> handleDmsApiException(DmsApiException ex) {
		String message = "API Error: " + ex.getLocalizedMessage();
		LOGGER.warn("API exception occured.", message);
		return new ResponseEntity<ExceptionData>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<?> handleException(Exception ex) {
		String message = "Server Error: " + ex.getLocalizedMessage();
		LOGGER.warn("Internal exception occured.", message);
		return new ResponseEntity<ExceptionData>(new ExceptionData(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public class ExceptionData {

		private String message;

		public ExceptionData(String msg) {
			this.message = msg;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

}
