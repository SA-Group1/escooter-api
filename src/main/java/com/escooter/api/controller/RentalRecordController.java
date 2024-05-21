package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.RentalRecord;
import com.escooter.api.service.RentalHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for handling rental record-related requests.
 */
@RestController
@RequestMapping("/api")
public class RentalRecordController {
    @Autowired
    private RentalHistoryService rentalHistoryService;
    
    /**
     * Retrieves rental records for a user.
     *
     * @param userDTO User data.
     * @return A ResponseEntity with HTTP status and rental records.
     */
    @PostMapping("/getRentalRecordList")
    public ResponseEntity<String> getRentalRecordList(@RequestBody UserDTO userDTO) {
        List<RentalRecord> rentalRecords = rentalHistoryService.getRentalRecordList(userDTO.getAccount(), userDTO.getPassword());
        JSONObject message = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            message.put("status", true);
            message.put("message", "return rental record");

            for (RentalRecord rentalRecord : rentalRecords) {
                String jsonString = objectMapper.writeValueAsString(rentalRecord);
                JSONObject rentalRecordJson = new JSONObject(jsonString);
                jsonArray.put(rentalRecordJson);
            }

            message.put("rentalRecords", jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}
}
