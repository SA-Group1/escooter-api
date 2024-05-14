package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.RentalRecord;
import com.escooter.api.model.User;

@Repository
public class RentalRecordRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public RentalRecord queryRentalRecordByUserId(String userId){
        String sql = "SELECT * FROM escooter_rental.rental_record Where user_id = ?";
        RowMapper<RentalRecord> rowMapper = (rs,rowNum) ->{
            User user = new User();
            user.setAccount(rs.getString("account"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }

    }
}
