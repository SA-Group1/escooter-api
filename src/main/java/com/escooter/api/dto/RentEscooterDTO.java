package com.escooter.api.dto;

import com.escooter.api.model.UserCredentials;

/**
 * DTO (Data Transfer Object) for renting an e-scooter.
 */
public class RentEscooterDTO {

    private UserCredentialsDTO userCredentialsDTO;
    private String escooterId;

    /**
     * Constructs a new RentEscooterDTO with the specified details.
     *
     * @param account     The user account.
     * @param password    The user password.
     * @param escooterId  The e-scooter ID.
     */
    public RentEscooterDTO(String account , String password, String escooterId){
        this.userCredentialsDTO = new UserCredentialsDTO(account, password);
        this.escooterId = escooterId;
    }

    public UserCredentials getUserCredentials() {
        return new UserCredentials(userCredentialsDTO.getAccount(),userCredentialsDTO.getPassword());
    }

    public String getEscooterId() {
        return escooterId;
    }
}




