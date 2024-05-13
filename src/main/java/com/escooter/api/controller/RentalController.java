package com.escooter.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.service.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/api")
public class RentalController {
    @Autowired
    RentalService rentalService;
    
}
