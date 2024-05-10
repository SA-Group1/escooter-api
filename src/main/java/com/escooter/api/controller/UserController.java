package com.escooter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.User;
// import com.escooter.api.service.;

@RestController
public class UserController {
    // @Autowired
    // User user;

    @GetMapping("/user/register")
    public void addUser() {
        
    }
}
