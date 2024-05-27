package com.escooter.api.exceptions;

public class RentEscooterFailException extends Exception {

    /**
     * Constructs a new RentEscooterFailException with the specified detail
     * message.
     *
     * @param message The detail message.
     */
    public RentEscooterFailException(String message) {
        super(message);
    }
}
