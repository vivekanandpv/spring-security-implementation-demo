package com.vivekanandpv.springsecurityimplementationdemo.exceptions;

public class GeneralAuthenticationException extends RuntimeException {
    public GeneralAuthenticationException() {
    }

    public GeneralAuthenticationException(String message) {
        super(message);
    }

    public GeneralAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralAuthenticationException(Throwable cause) {
        super(cause);
    }

    public GeneralAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
