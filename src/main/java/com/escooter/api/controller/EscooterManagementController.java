package com.escooter.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.EscooterDTO;
import com.escooter.api.service.EscooterService;
import com.escooter.api.util.JsonResponseBuilder;

/**
 * Controller for handling e-scooter related requests.
 */
@RestController
@RequestMapping("/api")
public class EscooterManagementController {

    private static final Logger logger = LoggerFactory.getLogger(EscooterSyncController.class);

    @Autowired
    private EscooterService escooterService;

    /**
     * Adds e-scooter data to the database and returns a success message.
     *
     * @param escooterDTO E-scooter data transfer object
     * @return A ResponseEntity with HTTP status and message
     */
    @PostMapping("/addEscooter")
    public ResponseEntity<String> addEscooter(@RequestBody EscooterDTO escooterDTO) {
        try {
            String escooterModelId = escooterDTO.getModelId();

            escooterService.addEscooter(escooterModelId);
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Adding e-scooter success"), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Failed to add e-scooter", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to add e-scooter"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets the list of e-scooter IDs and returns the list.
     *
     * @return A ResponseEntity with HTTP status and message
     */
    @GetMapping("/getEscooterIdList")
    public ResponseEntity<String> getEscooterIdList() {
        try {
            List<String> escooterIds = escooterService.getEscooterId();
            JSONArray jsonArray = new JSONArray();
            
            for (String escooterId : escooterIds) {
                jsonArray.put(new JSONObject().put("escooterId", escooterId));
            }
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get e-scooter ID list success", jsonArray), HttpStatus.OK);
        } catch (JSONException ex) {
            logger.error("Failed to get e-scooter ID list", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to get e-scooter ID list"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}