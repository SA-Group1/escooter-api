package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.RentalRecord;
import com.escooter.api.service.RentalHistoryService;
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
    @PostMapping("/rentalrecord")
    public String getRentRecordList(@RequestBody RentalRecord rentalRecord) {
        boolean res1 = rentalHistoryService
        
        
    }
    
    
}
