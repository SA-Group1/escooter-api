package com.escooter.api.dto;

/**
 * DTO (Data Transfer Object) for representing member card information.
 */
public class MemberCardDTO {
    private String cardNumber;
    private String expirationDate;
    
    /**
     * Constructs a new MemberCardDTO with the specified card details.
     * @param cardNumber The member card number.
     * @param expirationDate The expiration date of the member card.
     */
    public MemberCardDTO(String cardNumber, String expirationDate) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
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
}
