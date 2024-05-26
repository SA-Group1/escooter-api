package com.escooter.api.model;

/**
 * Represents a member card.
 */
public class MemberCard {
    private String cardNumber;
    private String expirationDate;
    
    public MemberCard(){}
    /**
     * Constructs a new MemberCard with the specified card details.
     * @param cardNumber The member card number.
     * @param expirationDate The expiration date of the member card.
     */
    public MemberCard(String cardNumber, String expirationDate) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    /**
     * Constructs a new MemberCard with the specified card details.
     * @param cardNumber The member card number.
     */
    public MemberCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Checks if the member card is valid.
     * @return True if the member card is valid, false otherwise.
     */
    public boolean isVaild() {
        // Logic of vaild member card
        return true;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}