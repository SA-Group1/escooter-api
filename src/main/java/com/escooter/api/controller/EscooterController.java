package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.*;
import com.escooter.api.service.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EscooterController {
    @Autowired
    Escooter escooter;
    @Autowired
    MaintenanceService maintenanceService;
    RentalService rentalService;

    //未完成
    @RequestMapping("/addEscooter")
    public String hello(@RequestParam String param) {
        return "Hello" ;
    }
    
}
