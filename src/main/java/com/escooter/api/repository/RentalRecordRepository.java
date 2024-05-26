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
     * @param account the user's account identifier.
     * @return a list of RentalRecord objects if found, otherwise null.
     */
    public List<RentalRecord> queryRentalRecordsByUserAccount(String account) {
        String sql = """
            SELECT 
                rental_record.*,
                escooter_model_type.model_id,
                escooter_model_type.fee_perminute,
                FLOOR(TIMESTAMPDIFF(MINUTE, rental_record.start_time, rental_record.end_time)) AS rental_duration,
                FLOOR(TIMESTAMPDIFF(MINUTE, rental_record.start_time, rental_record.end_time) * escooter_model_type.fee_perminute) AS rental_cost
            FROM escooter_rental.rental_record
            JOIN escooter_rental.escooter
            ON escooter.escooter_id = rental_record.escooter_id
            JOIN escooter_rental.escooter_model_type
            ON escooter_model_type.model_id = escooter.model_id
            WHERE user_id = (SELECT user_id FROM escooter_rental.user WHERE account = ?) AND end_time IS NOT NULL
        """;
        RowMapper<RentalRecord> rowMapper = (rs,rowNum) -> {
            RentalRecord rentalRecord = new RentalRecord();
            rentalRecord.setUserId(rs.getInt("user_id"));
            rentalRecord.setEscooterId(rs.getString("escooter_id"));
            rentalRecord.setStartTime(rs.getTimestamp("start_time").toLocalDateTime().toString());
            rentalRecord.setEndTime(rs.getTimestamp("end_time").toLocalDateTime().toString());
            rentalRecord.setIsPaid(rs.getBoolean("ispaid"));
            rentalRecord.setModelId(rs.getString("model_id"));
            rentalRecord.setFeePerMinutes(rs.getDouble("fee_perminute"));
            rentalRecord.setDuration(rs.getInt("rental_duration"));
            rentalRecord.setTotalFee(rs.getInt("rental_cost"));
            
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
     *
     * @param userId     The ID of the user renting the e-scooter.
     * @param escooterId The ID of the e-scooter being rented.
     * @return true if the rental record was successfully created.
     */
    public boolean createRentalRecord(int userId, String escooterId) {
        String sql = "INSERT INTO escooter_rental.rental_record (user_id, escooter_id, start_time) VALUES (?, ?, NOW())";
        jdbcTemplate.update(sql, userId, escooterId);
        return true;
    }
}
