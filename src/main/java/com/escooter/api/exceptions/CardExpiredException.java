package com.escooter.api.exceptions;

/**
 * Exception thrown when a credit card has expired.
 */
public class CardExpiredException extends Exception {
    /**
     * Constructs a new CardExpiredException with the specified detail message.
     * @param message The detail message.
     */
    public CardExpiredException(String message) {
        super(message);
    }
}
