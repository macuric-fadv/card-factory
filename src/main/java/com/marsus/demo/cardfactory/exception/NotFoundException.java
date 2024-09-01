package com.marsus.demo.cardfactory.exception;

/**
 * An exception class denoting that requesting resource has not been found.
 */
public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
