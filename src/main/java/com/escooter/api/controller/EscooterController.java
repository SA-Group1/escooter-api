package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.EscooterDTO;
import com.escooter.api.dto.GPSDTO;
import com.escooter.api.dto.UpdateGPSDTO;
import com.escooter.api.model.GPS;
import com.escooter.api.service.*;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api")
public class EscooterController {
    @Autowired
    private EscooterService escooterService;

    @PostMapping("/addEscooter")
    public ResponseEntity<String> addEscooter(@RequestBody EscooterDTO escooterDTO) {
        escooterService.addEscooter(escooterDTO.getModelId());
        
        // create return message
		JSONObject message = new JSONObject();
		try {
			message.put("status", true);
			message.put("message", "Adding escooter success");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    @PostMapping("/isRent")
    public ResponseEntity<String> isRent(@RequestBody EscooterDTO escooterDTO) {
        boolean res = escooterService.isRent(escooterDTO.getEscooterId());

        // create return message
		JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "escooter is rentable" : "escooter is not rentable");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
    
    @PostMapping("/isReturn")
    public ResponseEntity<String> isReturn(@RequestBody EscooterDTO escooterDTO) {
        boolean res = escooterService.isReturn(escooterDTO.getEscooterId());

        // create return message
		JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "escooter has been returned" : "escooter status is Rented");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
    
    @PostMapping("/getParkingStatus")
    public ResponseEntity<String> getParkingStatus(@RequestBody EscooterDTO escooterDTO) {
        boolean res = escooterService.getParkingStatus(escooterDTO.getEscooterId());

        // create return message
		JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "escooter is parking" : "escooter is not parking");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
    
    @PutMapping("updateGps")
    public ResponseEntity<String> updateGps(@RequestBody UpdateGPSDTO updateGPSDTO) {
        String escooterId = updateGPSDTO.getEscooterDTO().getEscooterId();
        GPSDTO gpsDTO = updateGPSDTO.getGPSDTO();
        GPS gps = new GPS(gpsDTO.getLongitude(), gpsDTO.getLatitude());
        
        boolean res = escooterService.updateGps(escooterId,gps);
        // create return message
        JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "GPS update success" : "GPS update failed");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    @PutMapping("updateBetteryLevel")
    public ResponseEntity<String> updateBetteryLevel(@RequestBody EscooterDTO escooterDTO) {
        String escooterId = escooterDTO.getEscooterId();
        int batteryLevel = escooterDTO.getBatteryLevel();
        
        boolean res = escooterService.updateBetteryLevel(escooterId,batteryLevel);
        // create return message
        JSONObject message = new JSONObject();
		try {
			message.put("status", res);
			message.put("message", res ? "Battery Level update success" : "Battery Level update failed");
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }
}
