package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.User;
// import com.escooter.api.service.;

@RestController
public class DatabaseController {
    // @Autowired
    // User user;

    @GetMapping("/api/addUser")
    public void addUser() {
        
    }

}
