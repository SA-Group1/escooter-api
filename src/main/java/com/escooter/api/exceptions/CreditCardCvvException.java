package com.escooter.api.exceptions;

/**
 * Exception thrown when the CVV (Card Verification Value) of a credit card is
 * invalid.
 */
public class CreditCardCvvException extends Exception {

    /**
     * Constructs a new CreditCardCvvException with the specified detail
     * message.
     *
     * @param message The detail message.
     */
    public CreditCardCvvException(String message) {
        super(message);
    }
}
