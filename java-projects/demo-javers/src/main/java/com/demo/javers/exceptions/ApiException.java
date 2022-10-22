package com.demo.javers.exceptions;

import org.springframework.http.HttpStatus;

import lombok.*;

@Getter
@ToString
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 8690249416476215529L;

	private String message;
	private HttpStatus httpStatus;

	public ApiException() {
		super();
	}

	public ApiException(String message) {
		super(message);
		this.message = message;
	}

	public ApiException(String message, HttpStatus httpStatus) {
		super(message);
		this.message = message;
		this.httpStatus = httpStatus;
	}

}
