package com.escooter.api.dto;

import com.escooter.api.model.UserCredentials;

/**
 * DTO (Data Transfer Object) for representing user credentials.
 */
public class UserCredentialsDTO {
    private final UserCredentials userCredentials;

    /**
     * Constructs a new UserCredentialsDTO with the specified account and password.
     *
     * @param account  The user's account.
     * @param password The user's password.
     */
    public UserCredentialsDTO(String account, String password) {
        this.userCredentials = new UserCredentials(account ,password);
    }

    public String getAccount() {
        return userCredentials.getAccount();
    }

    public String getPassword() {
        return userCredentials.getPassword();
    }

    public UserCredentials getUserCredentials(){
        return userCredentials;
    }
}
