package com.escooter.api.exceptions;

public class EscooterNotInReturnAreaException extends Exception {

    /**
     * Constructs a new EscooterNotInReturnAreaException with the specified
     * detail message.
     *
     * @param message The detail message.
     */
    public EscooterNotInReturnAreaException(String message) {
        super(message);
    }
}
