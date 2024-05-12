package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.service.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class EscooterController {
    @Autowired
    private EscooterService escooterService;
    private MaintenanceService maintenanceService;
    private RentalService rentalService;

    //寫入Escooter的資料
    @RequestMapping("/addEscooter")
    public String addEscooter() {
        escooterService.addEscooter(1,"LCE151","Avaliable");
        return "New Escooter data added";
    }
    
    
}
