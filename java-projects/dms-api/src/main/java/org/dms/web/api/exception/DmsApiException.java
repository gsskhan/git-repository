package org.dms.web.api.exception;

public class DmsApiException extends Exception {

	private static final long serialVersionUID = 3563783503131065100L;

	public DmsApiException() {
		super();
	}

	public DmsApiException(String message) {
		super(message);
	}

	public DmsApiException(Throwable throwable) {
		super(throwable);
	}

	public DmsApiException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
