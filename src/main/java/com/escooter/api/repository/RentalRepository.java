package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.RentalRecord;

/**
 * Repository for managing rental-related data in the database.
 */
@Repository
public class RentalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Checks if a given point (longitude and latitude) is within a designated
     * return area.
     *
     * @param longitude the longitude of the point to check.
     * @param latitude the latitude of the point to check.
     * @return true if the point is within the return area, false otherwise.
     */
    public boolean checkWithinReturnArea(double longitude, double latitude) {
        String sql = "SELECT COUNT(*) FROM escooter_rental.return_area WHERE ST_WITHIN(ST_GeomFromText(CONCAT('POINT(', ?, ' ', ?, ')')), area_point)";
        try {
            int res = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{latitude, longitude});
            return res > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    /**
     * Processes the return of an e-scooter, updating its status and rental
     * record.
     *
     * @param userId the ID of the user returning the e-scooter.
     * @param escooterId the ID of the e-scooter being returned.
     * @param modelId the model ID of the e-scooter.
     * @return true if the return was processed successfully, false otherwise.
     */
    public RentalRecord returnEscooter(int userId, String escooterId, String modelId) {
        String updateSql1 = "UPDATE escooter_rental.escooter SET escooter_status = \"Available\" WHERE escooter_id = ? AND escooter_status = \"Rented\"";
        String updateSql2 = "UPDATE escooter_rental.rental_record SET end_time = NOW() WHERE escooter_id = ? AND ispaid = 0";
        String updateSql3 = "UPDATE escooter_rental.rental_record SET ispaid = 1 WHERE escooter_id = ? AND ispaid = 0";

        String selectSql = """
            SELECT 
                rental_record.user_id,
                rental_record.rental_record_id,
                rental_record.escooter_id,
                DATE_FORMAT(rental_record.start_time, '%Y-%m-%d %H:%i') AS start_time,
                DATE_FORMAT(rental_record.end_time, '%Y-%m-%d %H:%i') AS end_time,
                rental_record.ispaid,
                escooter.model_id,
                escooter_model_type.fee_perminute,
                TIMESTAMPDIFF(MINUTE, rental_record.start_time, rental_record.end_time) AS duration,
                CEIL(TIMESTAMPDIFF(SECOND, rental_record.start_time, rental_record.end_time) * escooter_model_type.fee_perminute / 60) AS total_fee
            FROM escooter_rental.rental_record
            JOIN escooter_rental.escooter ON rental_record.escooter_id = escooter.escooter_id
            JOIN escooter_rental.escooter_model_type ON escooter.model_id = escooter_model_type.model_id
            WHERE rental_record.escooter_id = ? AND rental_record.user_id = ? AND rental_record.end_time IS NOT NULL
            ORDER BY rental_record.end_time DESC LIMIT 1;
        """;

        try {
            jdbcTemplate.update(updateSql1, escooterId);
            jdbcTemplate.update(updateSql2, escooterId);
            jdbcTemplate.update(updateSql3, escooterId);

            RowMapper<RentalRecord> rowMapper = (rs, rowNum) -> {
                RentalRecord rentalRecord = new RentalRecord();
                rentalRecord.setUserId(rs.getInt("user_id"));
                rentalRecord.setEscooterId(rs.getString("escooter_id"));
                rentalRecord.setStartTime(rs.getString("start_time"));
                rentalRecord.setEndTime(rs.getString("end_time"));
                rentalRecord.setIsPaid(rs.getBoolean("ispaid"));
                rentalRecord.setModelId(rs.getString("model_id"));
                rentalRecord.setFeePerMinutes(rs.getDouble("fee_perminute"));
                rentalRecord.setDuration(rs.getInt("duration"));
                rentalRecord.setTotalFee(rs.getInt("total_fee"));
                return rentalRecord;
            };

            return jdbcTemplate.queryForObject(selectSql, rowMapper, new Object[]{escooterId, userId});

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
