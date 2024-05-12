package com.escooter.api.dto;

/**
 * DTO (Data Transfer Object) for representing credit card information.
 */
public class CreditCardDTO {
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String cvv;
    
    /**
     * Constructs a new CreditCardDTO with the specified card details.
     * @param cardNumber The credit card number.
     * @param expirationDate The expiration date of the credit card.
     * @param cardHolderName The name of the card holder.
     * @param cvv The CVV (Card Verification Value) of the credit card.
     */
    public CreditCardDTO(String cardNumber, String expirationDate , String cardHolderName , String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
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
    public String getCardHolderName(){
        return cardHolderName;
    }

    /**
     * Returns the CVV (Card Verification Value) of the credit card.
     * @return The CVV.
     */
    public String getCvv(){
        return cvv;
    }
}