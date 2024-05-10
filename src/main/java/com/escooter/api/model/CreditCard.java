package com.escooter.api.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCard {
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    
    public CreditCard(String cardNumber, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String getExpirationDate() {
        return expirationDate;
    }

    public boolean isVaild() {
        // 如何驗證
        return true;
    }
}
