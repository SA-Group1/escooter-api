package com.escooter.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
import com.escooter.api.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    private List<User> userList;

    public boolean register(String account, String password, String userName, String email) {
        User user = userRepository.queryUserByAccount(account);
        boolean res;
        if (user == null) {
            res = userRepository.createUser(account, password, userName, email);
            return true;
        } else {
            return false;
        }
    }
    
    public User login(String account, String password) {
        User user = userRepository.queryUserByAccount(account);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean updateUserData(UserDTO userDTO) {
        // 判斷
        return userRepository.updateUserData(userDTO);
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
