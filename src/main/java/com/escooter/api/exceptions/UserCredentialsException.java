package com.escooter.api.exceptions;
/**
 * Exception thrown when user credentials are invalid.
 */
public class UserCredentialsException extends Exception{
    /**
     * Constructs a new UserCredentialsException with the specified detail message.
     * @param message The detail message.
     */
    public UserCredentialsException(String message) {
        super(message);
    }
}
