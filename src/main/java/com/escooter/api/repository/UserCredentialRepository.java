package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserCredentialRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean verifyUserCredentials(String account , String password){
        String sql = "SELECT COUNT(*) FROM escooter_rental.user WHERE (account = ? AND password = ?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,new Object[]{account, password});
        return count != null && count > 0;
    }
}
