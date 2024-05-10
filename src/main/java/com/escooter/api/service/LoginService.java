package com.escooter.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.User;

@Service
public class LoginService {
    @Autowired
    private List<User> userList;

    public User login(String account, String password) {
        // 
        return new User();
    }

    public boolean register(String account, String password, String userName) {
        // 
        return true;
    }
}
