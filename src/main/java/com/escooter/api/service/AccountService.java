package com.escooter.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.User;
import com.escooter.api.model.UserCredentials;

/**
 * Service class for managing user login and registration.
 */
@Service
public class AccountService {
    @Autowired
    UserCredentialService userCredentialService;
    @Autowired
    UserService userService;

    /**
     * Registers a new user.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @param userName The name of the user.
     * @param email The email address of the user.
     * @param phoneNumber
     * @return True if registration is successful, false if the account already exists.
     */
    public boolean register(String account, String password, String userName, String email,String phoneNumber) {
        User user = userService.queryUserByAccount(account);
        if (user == null) {
            boolean res = userService.createUser(account, password, userName, email, phoneNumber);
            return res;
        } else {
            return false;
        }
    }

    /**
     * Logs in a user.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @return The user if login is successful, null otherwise.
     */
    public boolean login(UserCredentials userCredentials) throws UserCredentialsException {
        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        return true;
    }

}
