package com.escooter.api.model;

/**
 * Represents a credit card.
 */
public class CreditCard {
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    
    public CreditCard(){}
    /**
     * Constructs a new CreditCard with the specified card details.
     * @param cardNumber The credit card number.
     * @param expirationDate The expiration date of the credit card.
     * @param cardHolderName The name of the card holder.
     */
    public CreditCard(String cardNumber, String expirationDate , String cardHolderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
    }

    /**
     * Constructs a new CreditCard with the specified card details.
     * @param cardNumber The credit card number.
     */
    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Returns the credit card number.
     * @return The credit card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Returns the expiration date of the credit card.
     * @return The expiration date.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Returns the name of the card holder.
     * @return The card holder name.
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     * Checks if the credit card is valid.
     * @param cvv The CVV (Card Verification Value) of the credit card.
     * @return True if the credit card is valid, false otherwise.
     */
    public boolean isVaild(String cvv) {
        // Logic of vaild credit card
        return true;
    }
}