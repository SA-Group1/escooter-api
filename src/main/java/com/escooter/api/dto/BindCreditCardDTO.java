package com.escooter.api.dto;

import com.escooter.api.model.CreditCard;
import com.escooter.api.model.UserCredentials;

/**
 * DTO (Data Transfer Object) for binding credit card to a user.
 */
public class BindCreditCardDTO {

    private final UserCredentialsDTO userCredentialsDTO;
    private final CreditCardDTO creditCardDTO;

    /**
     * Constructs a new BindCreditCardDTO with the specified details.
     *
     * @param account User account for credentials.
     * @param password User password for credentials.
     * @param cardNumber
     * @param expirationDate
     * @param cardHolderName
     * @param cvv
     */
    public BindCreditCardDTO(String account, String password, String cardNumber, String expirationDate, String cardHolderName, String cvv) {
        this.userCredentialsDTO = new UserCredentialsDTO(account, password);
        this.creditCardDTO = new CreditCardDTO(cardNumber, expirationDate, cardHolderName, cvv);
    }

    public UserCredentials getUserCredentials() {
        UserCredentials userCredentials = new UserCredentials(userCredentialsDTO.getAccount(), userCredentialsDTO.getPassword());
        return userCredentials;
    }

    public CreditCard getCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
        creditCard.setCardHolderName(creditCardDTO.getCardHolderName());
        return creditCard;
    }

    public String getCvv() {
        return creditCardDTO.getCvv();
    }
}
