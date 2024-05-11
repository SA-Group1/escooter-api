package com.escooter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escooter.api.model.User;
import com.escooter.api.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    LoginService loginService;

    @GetMapping("/users")
    public List<User> queryUsers() {
        return loginService.queryUsers();
    }

    @GetMapping("/users/{id}")
    public User queryUserById(@PathVariable Integer id) {
        return loginService.queryUserById(id);
    }

    @PostMapping("users")
    public User createUser(@RequestBody User user) {
        
        return loginService.createUser(user);
    }

    @PutMapping("users/{id}")
    public void updateUser(@RequestBody User user) {        
        
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        loginService.deleteUser(id);
    }

    @GetMapping("login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        loginService.login(account, password);
        return new String();
    }
    
}
