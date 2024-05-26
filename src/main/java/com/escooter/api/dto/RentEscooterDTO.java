package com.escooter.api.dto;

import com.escooter.api.model.UserCredentials;

/**
 * DTO (Data Transfer Object) for binding credit card to user.
 */
public class RentEscooterDTO {

    private UserCredentialsDTO userCredentialsDTO;
    private String escooterId;

    /**
     * Constructs a new RentEscooterDTO with the specified details.
     * @param userDTO The user data.
     * @param escooterDTO The escooter data.
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




