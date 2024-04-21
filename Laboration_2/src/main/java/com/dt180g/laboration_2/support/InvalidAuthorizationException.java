package com.dt180g.laboration_2.support;

/**
 * Represents an exception that is thrown when authorization is invalid.
 * @author Frank Curwen
 */
public class InvalidAuthorizationException extends RuntimeException {
    /**
     * Constructor method for InvalidAuthorizationException.
     * @param message String, the message to include in the exception.
     */
    public InvalidAuthorizationException(String message) {
        super(message);
    }
}
