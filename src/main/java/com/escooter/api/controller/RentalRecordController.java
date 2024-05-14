package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.RentalRecordDTO;
import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.RentalRecord;
import com.escooter.api.service.RentalHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class RentalRecordController {
    @Autowired
    private RentalHistoryService rentalHistoryService;
    @PostMapping("/getRentalRecordList")
    public ResponseEntity<String> getRentalRecordList(@RequestBody UserDTO userDTO) {
        List<RentalRecord> rentalRecords = rentalHistoryService.getRentalRecordList(userDTO.getAccount(), userDTO.getPassword());
        JSONObject message = new JSONObject();
		JSONObject rentalRecordMessage = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{}";

		try {
			message.put("status", true);
			message.put("message", "return rental record");
            for (int i=0; i<rentalRecords.size(); i++) {
                RentalRecord rentalRecord = rentalRecords.get(i);
                jsonString = objectMapper.writeValueAsString(rentalRecord);
                JSONObject jsonObject = new JSONObject(jsonString);
                rentalRecordMessage.put("rentalRecord"+ (i + 1), jsonObject);
            }
            message.put("rentalRecords", rentalRecordMessage);
		} catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}
}
