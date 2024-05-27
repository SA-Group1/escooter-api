package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.UserCredentialsDTO;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.RentalRecord;
import com.escooter.api.service.RentalRecordService;
import com.escooter.api.utils.JsonResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller for handling rental requests.
 */
@RestController
@RequestMapping("/api")
public class RentalRecordController {

    @Autowired
    private RentalRecordService rentalRecordService;

    /**
     * Retrieves rental records for a user.
     *
     * @param userCredentialsDTO User data.
     * @return A ResponseEntity with HTTP status and rental records.
     */
    @PostMapping("/getRentalRecordList")
    public ResponseEntity<String> getRentalRecordList(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        try {
            List<RentalRecord> rentalRecords = rentalRecordService.getRentalRecordList(userCredentialsDTO.getUserCredentials());
            JSONArray jsonArray = new JSONArray();
            ObjectMapper objectMapper = new ObjectMapper();

            for (RentalRecord rentalRecord : rentalRecords) {
                String jsonString = objectMapper.writeValueAsString(rentalRecord);
                JSONObject rentalRecordJson = new JSONObject(jsonString);
                jsonArray.put(rentalRecordJson);
            }
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get rental records success.", jsonArray), HttpStatus.OK);
        } catch (JsonProcessingException | JSONException e) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Get rental records failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
        }
    }
}
