package com.escooter.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.GPSDTO;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
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





@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    RentalService rentalService;
    
    @Autowired
    EscooterService escooterService;

    @PostMapping("getRentableEscooterList")
    public ResponseEntity<String> postMethodName(@RequestBody GPSDTO gpsDTO) {
        List<Escooter> escooters = rentalService.showAvailableEscooter(new GPS(gpsDTO.getLongitude(), gpsDTO.getLatitude()));
        JSONObject message = new JSONObject();
        
        if (escooters.isEmpty()) {
            try {
                message.put("status", false);
                message.put("message", "ERROR");
                message.put("escooters", new JSONObject("{}"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(message.toString(), HttpStatus.OK);
        }

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
    
}
