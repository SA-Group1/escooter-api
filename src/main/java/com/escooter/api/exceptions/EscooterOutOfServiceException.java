package com.escooter.api.exceptions;

public class EscooterOutOfServiceException extends Exception {

    /**
     * Constructs a new EscooterOutOfServiceException with the specified detail
     * message.
     *
     * @param message The detail message.
     */
    public EscooterOutOfServiceException(String message) {
        super(message);
    }
}
