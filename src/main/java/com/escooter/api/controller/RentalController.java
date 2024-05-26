package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.GpsDTO;
import com.escooter.api.dto.RentEscooterDTO;
import com.escooter.api.dto.UserCredentialsDTO;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
import com.escooter.api.service.RentalService;
import com.escooter.api.utils.JsonResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Controller for handling rental related requests.
 */
@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    RentalService rentalService;

    /**
	 * Adding rental data to database and returns a successful message
	 *
     * @param gpsDTO GPS data 
	 * @return A ResponseEntity with http status and message
	 */
    @PostMapping("getRentableEscooterList")
    public ResponseEntity<String> getRentableEscooterList(@RequestBody GpsDTO gpsDTO) {
        List<Escooter> escooters = rentalService.showAvailableEscooter(new GPS(gpsDTO.getLongitude(), gpsDTO.getLatitude()));

        if (escooters.isEmpty()) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Escooters not found."), HttpStatus.NO_CONTENT);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JSONArray jsonArray = new JSONArray();
            String jsonString = "{}";

            for (Escooter escooter:escooters) {
                jsonString = objectMapper.writeValueAsString(escooter);
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonArray.put(jsonObject);
            }

            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Get rentable escooter success.",jsonArray),HttpStatus.OK);
        } catch (JSONException | JsonProcessingException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Get rentable escooter failed."),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    

    /**
     * Rents an e-scooter.
     *
     * @param rentEscooterDTO DTO containing e-scooter and user data.
     * @return A ResponseEntity with HTTP status and rental information.
     */
    @PostMapping("/rentEscooter")
    public ResponseEntity<String> rentEscooter(@RequestBody RentEscooterDTO rentEscooterDTO) {
		try {
            Escooter escooter = rentalService.rentEscooter(rentEscooterDTO.getUserCredentials(), rentEscooterDTO.getEscooterId());
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = "{}";
            jsonString = objectMapper.writeValueAsString(escooter);
            JSONObject jsonObject = new JSONObject(jsonString);
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Rent escooter success.",jsonObject),HttpStatus.OK);
		} catch (JSONException | JsonProcessingException e) {
			return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Rent escooter failed."),HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		}
    }

    /**
    * Updates the parking status of the e-scooter associated with the provided user account.
    *
    * @param userDTO User data transfer object containing account and password
     * @return A ResponseEntity with HTTP status and message
    */
    @PutMapping("/updateEscooterParkStatus")
    public ResponseEntity<String> updateEscooterParkStatus(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        try {
            boolean res = rentalService.updateEscooterParkStatus(userCredentialsDTO.getUserCredentials());
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Update escooter park status success.",res),HttpStatus.OK);
		} catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		}
    }

    /**
    * Processes the return of an e-scooter and payment associated with the provided user account.
    *
    * @param userDTO User data transfer object containing account and password
    * @return A ResponseEntity with HTTP status and message
    */
    @PostMapping("/returnEscooter")
    public ResponseEntity<String> returnEscooter(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		try {
            boolean res = rentalService.returnEscooter(userCredentialsDTO.getUserCredentials());
            return new ResponseEntity<>(JsonResponseBuilder.buildSuccessResponse("Rent escooter success.",res),HttpStatus.OK);
		} catch (UserCredentialsException ex) {
            return new ResponseEntity<>(JsonResponseBuilder.buildErrorResponse("Invalid user credentials."), HttpStatus.UNAUTHORIZED);
		}
    }
    
}
