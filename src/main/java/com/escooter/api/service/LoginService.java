package com.escooter.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
import com.escooter.api.repository.UserRepository;

/**
 * Service class for managing user login and registration.
 */
@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    private List<User> userList;

    /**
     * Registers a new user.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @param userName The name of the user.
     * @param email The email address of the user.
     * @return True if registration is successful, false if the account already exists.
     */
    public boolean register(String account, String password, String userName, String email) {
        User user = userRepository.queryUserByAccount(account);
        boolean res;
        if (user == null) {
            res = userRepository.createUser(account, password, userName, email);
            return true;
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
    public boolean login(String account, String password) {
        User user = userRepository.queryUserByAccount(account);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

        /**
     * user information.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @return The user data.
     */
    public User getUserData(String account, String password) {
        User user = userRepository.queryUserByAccount(account);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Updates user data.
     * @param userDTO The data transfer object containing updated user information.
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateUserData(UserDTO userDTO) {
        // 判斷
        return userRepository.updateUserData(userDTO);
    }

    /**
     * Queries all users.
     * @return A list of all users.
     */
    public List<User> queryUsers() {
        User user = new User();
        return this.userList;
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

    public void deleteUser(Integer id) {
        
    }
}
