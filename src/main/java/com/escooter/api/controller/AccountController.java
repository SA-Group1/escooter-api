package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.escooter.api.dto.UserCredentialsDTO;
import com.escooter.api.dto.UserDTO;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.service.AccountService;
import com.escooter.api.utils.JsonResponseBuilder;

public class AccountController {
    @Autowired
    AccountService accountService;
    
    /**
	 * Adds user data to the database and returns a success message.
	 *
	 * @param userDTO User data.
	 * @return A ResponseEntity with HTTP status and message.
	 */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            accountService.register(userDTO.getAccount(), userDTO.getPassword(), userDTO.getUserName(), userDTO.getEmail(), userDTO.getPhoneNumber());
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Create user success."),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Create user failed."),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Authenticates user login.
     *
     * @param userDTO User data.
     * @return A ResponseEntity with HTTP status and message.
     */
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDTO userCredentialsDTO) {

        try {
            boolean res = accountService.login(userCredentialsDTO.getUserCredentials());
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Login success.",res),HttpStatus.OK);
        } catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		}
    }
}
