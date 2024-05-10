package com.escooter.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/hola")
    String hola(){
        return "{\"account\":\"1234123412341234\",\"password\":\"1234\"}";
    }
}
