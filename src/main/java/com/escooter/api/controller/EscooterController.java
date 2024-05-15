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
import com.escooter.api.service.*;




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
    
}
