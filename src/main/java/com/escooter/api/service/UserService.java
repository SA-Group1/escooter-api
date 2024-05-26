package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.User;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCredentialService userCredentialService;

    public User queryUserByAccount(String account){
        return userRepository.queryUserByAccount(account);
    }

    public int queryUserIdByAccount(String account){
        return userRepository.queryUserIdByAccount(account);
    }

    public boolean createUser(String account, String password, String userName, String email, String phoneNumber){
        return userRepository.createUser(account, password, userName, email, phoneNumber);
    }

    /**
     * user information.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @return The user data.
     */
    public User getUserData(UserCredentials userCredentials) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        return userRepository.queryUserByAccount(userCredentials.getAccount());
    }

    /**
     * Updates user data.
     * @param userDTO The data transfer object containing updated user information.
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateUserData(UserCredentials userCredentials, User user) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        return userRepository.updateUserData(user);
    }

    /**
     * Upload user photo.
     * @param userDTO The data transfer object containing updated user information.
     * @return True if the update is successful, false otherwise.
     */
    public boolean uploadUserPhoto(UserCredentials userCredentials,byte[] image) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        return userRepository.uploadUserPhoto(userCredentials.getAccount(),image);
    }

    /**
     * Queries a user by their ID.
     * @param id The ID of the user.
     * @return The user if found, null otherwise.
     */
    public User queryUserById(Integer id) {
        User user = new User();
        return user;
    }

    // 建立使用者
    public User createUser(User user) {
        return user;
    }


    /**
     * user photo.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @return The user photo.
     */
    public User getUserPhoto(UserCredentials userCredentials) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        return userRepository.queryUserByAccount(userCredentials.getAccount());
    }
    
}
