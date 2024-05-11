package com.escooter.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.User;
import com.escooter.api.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    private List<User> userList;

    public User login(String account, String password) {
        // 
        return new User();
    }

    public boolean register(String account, String password, String userName) {
        
        return true;
    }

    // 查詢所有使用者
    public List<User> queryUsers() {
        User user = new User();
        return this.userList;
    }

    // 查詢使用者
    public User queryUserById(Integer id) {
        User user = new User();
        return user;
    }

    // 建立使用者
    public User createUser(User user) {
        return user;
    }

    public void deleteUser(Integer id) {
        
    }
}
