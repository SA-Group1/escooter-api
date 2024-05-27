package com.escooter.api.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.UploadUserPhotoDTO;
import com.escooter.api.dto.UserCredentialsDTO;
import com.escooter.api.dto.UserDTO;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.User;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.service.UserService;
import com.escooter.api.utils.JsonResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller for handling user-related requests.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * get user data.
     *
     * @param userCredentialsDTO User data.
     * @return A ResponseEntity with HTTP status and user data.
     */
    @PostMapping("/getUserData")
    public ResponseEntity<String> getUserData(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        // Calls service to retrieve user data

        try {
            User user = userService.getUserData(userCredentialsDTO.getUserCredentials());

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(user);
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonObject.remove("image");
            jsonObject.getJSONObject("creditCard").remove("cardHolderName");

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get user data success.", jsonObject), HttpStatus.OK);
        } catch (JsonProcessingException | JSONException e) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Get user data failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Updates user data.
     *
     * @param userDTO User data.
     * @return A ResponseEntity with HTTP status and message.
     */
    @PutMapping("/updateUserData")
    public ResponseEntity<String> updateUserData(@RequestBody UserDTO userDTO) {
        try {
            boolean res = userService.updateUserData(new UserCredentials(userDTO.getAccount(), userDTO.getPassword()), userDTO.getUser());
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Update user data success.", res), HttpStatus.OK);
        } catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Upload user photo
     *
     * @param uploadUserPhotoDTO User data.
     * @return A ResponseEntity with HTTP status and message.
     */
    @PutMapping("/uploadUserPhoto")
    public ResponseEntity<String> uploadUserPhoto(@RequestBody UploadUserPhotoDTO uploadUserPhotoDTO) {
        try {
            boolean res = userService.uploadUserPhoto(uploadUserPhotoDTO.getUserCredentials(), uploadUserPhotoDTO.getImage());
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Update user photo success.", res), HttpStatus.OK);
        } catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Something wrong."), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * get user photo.
     *
     * @param userCredentialsDTO User data.
     * @return A ResponseEntity with user photo.
     */
    @PostMapping("/getUserPhoto")
    public ResponseEntity<String> getUserPhoto(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        try {
            User user = userService.getUserPhoto(userCredentialsDTO.getUserCredentials());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("image", Base64.getEncoder().encodeToString(user.getImage()));

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get user photo success.", jsonObject), HttpStatus.OK);
        } catch (JSONException e) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Get user photo failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
        }
    }

}
