package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
import com.escooter.api.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    LoginService loginService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        boolean rs = loginService.register(userDTO.getAccount(), userDTO.getPassword(), userDTO.getUserName());
        
        // create return message
		JSONObject message = new JSONObject();
        if (rs) {
            try {
                message.put("status", true);
                message.put("message", "create user success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(message.toString(), HttpStatus.OK);
        }
        try {
            message.put("status", false);
            message.put("message", "create user failed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User user = loginService.login(userDTO.getAccount(), userDTO.getPassword());
        
        // create return message
		JSONObject message = new JSONObject();
        
        if (user == null) {
            try {
                message.put("status", false);
                message.put("message", "login failed");
                message.put("user", new JSONObject("{}"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(message.toString(), HttpStatus.OK);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{}";
        try {
            jsonString = objectMapper.writeValueAsString(user);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
			message.put("status", true);
			message.put("message", "login success");
            message.put("user", jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    // @GetMapping("/users")
    // public List<User> queryUsers() {
    //     return loginService.queryUsers();
    // }

    // @GetMapping("/users/{id}")
    // public User queryUserById(@PathVariable Integer id) {
    //     return loginService.queryUserById(id);
    // }

    // @PostMapping("users")
    // public User createUser(@RequestBody User user) {
        
    //     return loginService.createUser(user);
    // }

    // @PutMapping("users/{id}")
    // public void updateUser(@RequestBody User user) {        
        
    // }

    // @DeleteMapping("users/{id}")
    // public void deleteUser(@PathVariable Integer id) {
    //     loginService.deleteUser(id);
    // }
}
