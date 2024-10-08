package org.dms.web.api.exception;

public class DmsRuntimeException extends RuntimeException {

    public DmsRuntimeException() {
        super();
    }

    public DmsRuntimeException(String message) {
        super(message);
    }

    public DmsRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public DmsRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
