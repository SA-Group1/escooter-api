package com.escooter.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.RentalRecord;

@Repository
public class RentalRecordRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<RentalRecord> queryRentalRecordsByUserId(String account){
        String sql = "SELECT * FROM escooter_rental.rental_record WHERE user_id = (SELECT user_id FROM escooter_rental.user WHERE account = ?)";
        RowMapper<RentalRecord> rowMapper = (rs,rowNum) -> {
            RentalRecord rentalRecord = new RentalRecord();
            rentalRecord.setUserAccount(account);
            rentalRecord.setEscooterId(rs.getString("escooter_id"));
            rentalRecord.setStartTime(rs.getTimestamp("start_time").toLocalDateTime().toString());
            rentalRecord.setEndTime(rs.getTimestamp("end_time").toLocalDateTime().toString());
            rentalRecord.setIsPaid(rs.getBoolean("ispaid"));
            return rentalRecord;
        };
        try {
			return jdbcTemplate.query(sql, rowMapper, account);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
    }
}
