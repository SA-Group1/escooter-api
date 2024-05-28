package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.UserCredentialRepository;

/**
 * Service class for managing user credential verification.
 */
@Service
public class UserCredentialService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    /**
     * Verifies user credentials.
     *
     * @param userCredentials The user credentials containing account and
     * password.
     * @return True if the credentials are valid, false otherwise.
     */
    public boolean verifyUserCredentials(UserCredentials userCredentials) {
        return userCredentialRepository.verifyUserCredentials(userCredentials.getAccount(), userCredentials.getPassword());
    }
}
