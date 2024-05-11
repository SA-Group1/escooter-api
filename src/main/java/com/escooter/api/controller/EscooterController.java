package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.*;
import com.escooter.api.service.*;


@RestController
public class EscooterController {
    @Autowired
    EscooterService escooterService;

    //未完成
    @RequestMapping("/addEscooter")
    public String hello() {
        Escooter escooter = new Escooter(1,"LCK121","Avaliable");
        escooterService.addEscooter(escooter);
        return "New Escooter added";
    }
    
}
