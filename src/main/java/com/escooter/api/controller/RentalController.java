package com.escooter.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.EscooterDTO;
import com.escooter.api.dto.GPSDTO;
import com.escooter.api.dto.RentEscooterDTO;
import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
import com.escooter.api.model.RentalRecord;
import com.escooter.api.model.User;
import com.escooter.api.service.EscooterService;
import com.escooter.api.service.RentalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * Controller for handling rental related requests.
 */
@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    RentalService rentalService;
    
    @Autowired
    EscooterService escooterService;

    /**
	 * Adding rental data to database and returns a successful message
	 *
	 * @param GPSDTO Credit GPS data 
	 * @return A ResponseEntity with http status and message
	 */
    @PostMapping("getRentableEscooterList")
    public ResponseEntity<String> postMethodName(@RequestBody GPSDTO gpsDTO) {
        // call service to show the avaliable escooter list
        List<Escooter> escooters = rentalService.showAvailableEscooter(new GPS(gpsDTO.getLongitude(), gpsDTO.getLatitude()));
        JSONObject message = new JSONObject();
        
        if (escooters.isEmpty()) {
            try {
                message.put("status", false);
                message.put("message", "ERROR: There are no available e-scooters for rent within the range.");
                message.put("escooters", new JSONObject("{}"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(message.toString(), HttpStatus.OK);
        }
        //creat return message
        JSONObject escooterMessage = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{}";
        try {
            message.put("status", true);
            message.put("message", "return escooters");
            
            for (int i=0; i<escooters.size(); i++) {
                Escooter escooter = escooters.get(i);
                jsonString = objectMapper.writeValueAsString(escooter);
                System.out.println(jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                escooterMessage.put("escooter"+ (i + 1), jsonObject);
            }
            message.put("escooters", escooterMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
    

    /**
     * Rents an e-scooter.
     *
     * @param rentEscooterDTO DTO containing e-scooter and user data.
     * @return A ResponseEntity with HTTP status and rental information.
     */
    @PostMapping("/rentEscooter")
    public ResponseEntity<String> rentEscooter(@RequestBody RentEscooterDTO rentEscooterDTO) {
        String escooterId = rentEscooterDTO.getEscooterDTO().getEscooterId();
        UserDTO userDTO = rentEscooterDTO.getUserDTO();
        User user = new User(userDTO.getAccount(), userDTO.getPassword());

        Escooter escooter = rentalService.rentEscooter(user, escooterId);

        // create return message
		JSONObject message = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{}";

		try {
			message.put("status", escooter != null);
			message.put("message", escooter != null ? "rent escooter success" : "rent escooter failed");
            jsonString = objectMapper.writeValueAsString(escooter);
            JSONObject jsonObject = new JSONObject(jsonString);
            message.put("escooter", escooter != null ? jsonObject : new JSONObject("{}"));
		} catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    @PutMapping("/updateEscooterParkStatus")
    public ResponseEntity<String> updateEscooterParkStatus(@RequestBody UserDTO userDTO) {
        boolean res = rentalService.updateEscooterParkStatus(userDTO.getAccount(), userDTO.getPassword());


        // create return message
		JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "update escooter park status success" : "update escooter park status failed");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
}
