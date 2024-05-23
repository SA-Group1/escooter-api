package com.escooter.api.exceptions;

public class CardExpiredException extends Exception {
    public CardExpiredException(String message) {
        super(message);
    }
}
