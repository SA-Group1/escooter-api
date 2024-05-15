package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
import com.escooter.api.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




/**
 * Controller for handling user-related requests.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    LoginService loginService;
    /**
	 * Adds user data to the database and returns a success message.
	 *
	 * @param userDTO User data.
	 * @return A ResponseEntity with HTTP status and message.
	 */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        // Calls service return the user register is sucess or fail
        boolean res = loginService.register(userDTO.getAccount(), userDTO.getPassword(), userDTO.getUserName(), userDTO.getEmail());
        
        // create return message
		JSONObject message = new JSONObject();
        if (res) {
            try {
                message.put("status", true);
                message.put("message", "create user success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(message.toString(), HttpStatus.OK);
        }
        try {
            message.put("status", false);
            message.put("message", "create user failed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    /**
     * Authenticates user login.
     *
     * @param userDTO User data.
     * @return A ResponseEntity with HTTP status and message.
     */
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        // Calls service to authenticate user login
        boolean res = loginService.login(userDTO.getAccount(), userDTO.getPassword());
        
        // Create return message
		JSONObject message = new JSONObject();
        
        try {
            message.put("status", res);
            message.put("message", res ? "login success":"login failed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Returns a successful response with user info
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    /**
     * get user data.
     *
     * @param userDTO User data.
     * @return A ResponseEntity with HTTP status and user data.
     */
    @PostMapping("getUserData")
    public ResponseEntity<String> getUserData(@RequestBody UserDTO userDTO) {
        // Calls service to retrieve user data
        User user = loginService.getUserData(userDTO.getAccount(), userDTO.getPassword());
        
        // Create return message
		JSONObject message = new JSONObject();
        
        if (user == null) {
            try {
                message.put("status", false);
                message.put("message", "get user data failed");
                message.put("user", new JSONObject("{}"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(message.toString(), HttpStatus.OK);
        }
        // Converts the User object to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{}";
        try {
            jsonString = objectMapper.writeValueAsString(user);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Converts the JSON string to a JSONObject
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
			message.put("status", true);
			message.put("message", "get user data success");
            message.put("user", jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        // Returns a successful response with user info
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    /**
     * Updates user data.
     *
     * @param userDTO User data.
     * @return A ResponseEntity with HTTP status and message.
     */
    @PutMapping("updateUserData")
    public ResponseEntity<String> updateUserData(@RequestBody UserDTO userDTO) {
        boolean res = loginService.updateUserData(userDTO);
        
        JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "update success":"update failed");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
}
