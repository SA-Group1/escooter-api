package com.escooter.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) for binding credit card to a user.
 */
public class BindCreditCardDTO {

    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("creditCard")
    private CreditCardDTO creditCardDTO;

    /**
     * Constructs a new BindCreditCardDTO with the specified details.
     * @param userDTO The user data.
     * @param creditCardDTO The credit card data.
     */
    public BindCreditCardDTO(UserDTO userDTO, CreditCardDTO creditCardDTO){
        this.userDTO = userDTO;
        this.creditCardDTO = creditCardDTO;
    }

    /**
     * Returns the user data.
     * @return The user data.
     */
    public UserDTO getUserDTO(){
        return userDTO;
    }

    /**
     * Returns the credit card data.
     * @return The credit card data.
     */
    public CreditCardDTO getCreditCardDTO(){
        return creditCardDTO;
    }
}




