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
     * @param expirationDate The expiration date of the member card.
     */
    public MemberCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Returns the member card number.
     * @return The member card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Returns the expiration date of the member card.
     * @return The expiration date.
     */
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
    /**
    * Sets the credit card number.
    *
    * @param cardNumber The credit card number to set
    */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
    * Sets the expiration date of the credit card.
    *
    * @param expirationDate The expiration date to set in MM/YY format
    */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}