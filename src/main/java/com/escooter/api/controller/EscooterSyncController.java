package com.escooter.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.escooter.api.dto.EscooterDTO;
import com.escooter.api.dto.UpdateGpsDTO;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
import com.escooter.api.service.EscooterService;
import com.escooter.api.utils.JsonResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller for handling e-scooter sync requests.
 */
@RestController
@RequestMapping("/api")
public class EscooterSyncController {

    private static final Logger logger = LoggerFactory.getLogger(EscooterSyncController.class);

    @Autowired
    private EscooterService escooterService;

    /**
     * Gets the status of the e-scooter and returns the status.
     *
     * @param escooterDTO E-scooter data transfer object
     * @return A ResponseEntity with HTTP status and message
     */
    @PostMapping("/getEscooterStatus")
    public ResponseEntity<String> getEscooterStatus(@RequestBody EscooterDTO escooterDTO) {
        try {
            String escooterId = escooterDTO.getEscooterId();
            String escooterStatus = escooterService.getEscooterStatus(escooterId);

            if (escooterStatus == null) {
                return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("E-scooter not found"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get status success", escooterStatus), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Failed to get status", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to get status"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets the status of the e-scooter and returns the status.
     *
     * @param escooterDTO E-scooter data transfer object
     * @return A ResponseEntity with HTTP status and message
     */
    @PostMapping("/getEscooter")
    public ResponseEntity<String> getEscooter(@RequestBody EscooterDTO escooterDTO) {
        try {
            String escooterId = escooterDTO.getEscooterId();
            Escooter escooter = escooterService.getEscooter(escooterId);

            if (escooter == null) {
                return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("E-scooter not found"), HttpStatus.NOT_FOUND);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(escooter);
            JSONObject jsonObject = new JSONObject(jsonString);

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get escooter success", jsonObject), HttpStatus.OK);
        } catch (JsonProcessingException | JSONException ex) {
            logger.error("Failed to get escooter", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to get escooter"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets the status of the e-scooter and returns the status.
     *
     * @param escooterDTO E-scooter data transfer object
     * @return A ResponseEntity with HTTP status and message
     */
    @PostMapping("/getEscooterGps")
    public ResponseEntity<String> getEscooterGpsById(@RequestBody EscooterDTO escooterDTO) {
        try {
            GPS escooterGps = escooterService.getEscooterGpsById(escooterDTO.getEscooterId());

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(escooterGps);
            JSONObject jsonObject = new JSONObject(jsonString);

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get escooter gps success", jsonObject), HttpStatus.OK);
        } catch (JsonProcessingException | JSONException ex) {
            logger.error("Failed to get status", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to get escooter gps"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the GPS location of the e-scooter and returns the status.
     *
     * @param updateGpsDTO Update GPS data transfer object
     * @return A ResponseEntity with HTTP status and message
     */
    @PutMapping("/updateGps")
    public ResponseEntity<String> updateGps(@RequestBody UpdateGpsDTO updateGpsDTO) {
        try {
            String escooterId = updateGpsDTO.getEscooterId();
            GPS gps = updateGpsDTO.getGps();

            escooterService.updateGps(escooterId, gps);

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("GPS update success"), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Failed to update GPS", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to update GPS"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the battery level of the e-scooter and returns the status.
     *
     * @param escooterDTO E-scooter data transfer object
     * @return A ResponseEntity with HTTP status and message
     */
    @PutMapping("/updateBatteryLevel")
    public ResponseEntity<String> updateBatteryLevel(@RequestBody EscooterDTO escooterDTO) {
        try {
            String escooterId = escooterDTO.getEscooterId();
            int batteryLevel = escooterDTO.getBatteryLevel();

            escooterService.updateBatteryLevel(escooterId, batteryLevel);

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Battery Level update success"), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Failed to update battery level", ex);
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Failed to update battery level"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
