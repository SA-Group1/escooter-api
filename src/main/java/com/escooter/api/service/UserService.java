package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.User;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.UserRepository;

/**
 * Service class for managing user operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCredentialService userCredentialService;

    /**
     * Queries a user by their account.
     *
     * @param account The account identifier for the user.
     * @return The user if found, null otherwise.
     */
    public User queryUserByAccount(String account) {
        return userRepository.queryUserByAccount(account);
    }

    /**
     * Queries the user ID by their account.
     *
     * @param account The account identifier for the user.
     * @return The user ID if found.
     */
    public int queryUserIdByAccount(String account) {
        return userRepository.queryUserIdByAccount(account);
    }

    /**
     * Creates a new user.
     *
     * @param account The user's account identifier.
     * @param password The user's password.
     * @param userName The user's name.
     * @param email The user's email.
     * @param phoneNumber The user's phone number.
     * @return True if the user is successfully created.
     */
    public boolean createUser(String account, String password, String userName, String email, String phoneNumber) {
        return userRepository.createUser(account, password, userName, email, phoneNumber);
    }

    /**
     * Retrieves user data.
     *
     * @param userCredentials The user credentials containing account and
     * password.
     * @return The user data.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public User getUserData(UserCredentials userCredentials) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
            throw new UserCredentialsException("Invalid credentials.");
        }

        return userRepository.queryUserByAccount(userCredentials.getAccount());
    }

    /**
     * Updates user data.
     *
     * @param userCredentials The user credentials.
     * @param user The user data to update.
     * @return True if the update is successful, false otherwise.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public boolean updateUserData(UserCredentials userCredentials, User user) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
            throw new UserCredentialsException("Invalid credentials.");
        }

        return userRepository.updateUserData(user);
    }

    /**
     * Uploads user photo.
     *
     * @param userCredentials The user credentials.
     * @param image The user photo as a byte array.
     * @return True if the update is successful, false otherwise.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public boolean uploadUserPhoto(UserCredentials userCredentials, byte[] image) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
            throw new UserCredentialsException("Invalid credentials.");
        }

        return userRepository.uploadUserPhoto(userCredentials.getAccount(), image);
    }

    /**
     * Queries a user by their ID.
     *
     * @param id The ID of the user.
     * @return The user if found, null otherwise.
     */
    public User queryUserById(Integer id) {
        User user = new User();
        return user;
    }

    /**
     * Creates a user.
     *
     * @param user The user data to create.
     * @return The created user.
     */
    public User createUser(User user) {
        // Dummy implementation for example purposes
        return user;
    }

    /**
     * Retrieves user photo.
     *
     * @param userCredentials The user credentials.
     * @return The user photo.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public User getUserPhoto(UserCredentials userCredentials) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
            throw new UserCredentialsException("Invalid credentials.");
        }

        return userRepository.queryUserByAccount(userCredentials.getAccount());
    }

}
