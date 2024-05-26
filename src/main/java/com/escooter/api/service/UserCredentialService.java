package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.UserCredentialRepository;

public class UserCredentialService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    public boolean verifyUserCredentials(UserCredentials userCredentials){
        return userCredentialRepository.verifyUserCredentials(userCredentials.getAccount() , userCredentials.getPassword());
    }
}
