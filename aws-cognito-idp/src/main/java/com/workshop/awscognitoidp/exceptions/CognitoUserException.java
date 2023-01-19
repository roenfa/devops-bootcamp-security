package com.workshop.awscognitoidp.exceptions;

public class CognitoUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CognitoUserException(String message) {
        super(message);
    }

    public CognitoUserException(String message, Throwable cause) {
        super(message, cause);
    }
}