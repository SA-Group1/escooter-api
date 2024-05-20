package com.escooter.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.RentalRecord;

/**
 * Repository for managing rental record data in the database.
 */
@Repository
public class RentalRecordRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Queries rental records by the user's account.
     * 
     * @param account the user's account identifier
     * @return a list of RentalRecord objects if found, otherwise null
     */
    public List<RentalRecord> queryRentalRecordsByUserAccount(String account) {
        String sql = "SELECT * FROM escooter_rental.rental_record WHERE user_id = (SELECT user_id FROM escooter_rental.user WHERE account = ?) AND end_time IS NOT NULL";
        RowMapper<RentalRecord> rowMapper = (rs,rowNum) -> {
            RentalRecord rentalRecord = new RentalRecord();
            rentalRecord.setUserId(rs.getInt("user_id"));
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

    /**
    * Creates a new rental record for the specified user and e-scooter.
    * @param userId     The ID of the user renting the e-scooter
    * @param escooterId The ID of the e-scooter being rented
    * @return true if the rental record was successfully created
    */
    public boolean createRentalRecord(int userId, String escooterId) {
        String sql = "INSERT INTO escooter_rental.rental_record (user_id, escooter_id, start_time) VALUES (?, ?, NOW())";
        jdbcTemplate.update(sql, userId, escooterId);
        return true;
    }
}
