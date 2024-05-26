package com.escooter.api.dto;

import com.escooter.api.model.UserCredentials;

public class UserCredentialsDTO {
    private final UserCredentials userCredentials;

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
