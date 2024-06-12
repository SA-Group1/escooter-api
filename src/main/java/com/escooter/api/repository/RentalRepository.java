package com.escooter.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.RentalRecord;
import com.escooter.api.model.ReturnArea;

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
     * Get escooter return area list.
     *
     * @return list of return area.
     */
    public List<ReturnArea> getReturnAreas() {
        String sql = "SELECT idreturn_area ,ST_AsText(area_point) as area_point FROM escooter_rental.return_area;";
        return jdbcTemplate.query(sql, new ReturnAreaRowMapper());
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
                rental_record.rental_record_id,
                rental_record.user_id,
                rental_record.rental_record_id,
                rental_record.escooter_id,
                DATE_FORMAT(rental_record.start_time, '%Y-%m-%d %H:%i') AS start_time,
                DATE_FORMAT(rental_record.end_time, '%Y-%m-%d %H:%i') AS end_time,
                rental_record.ispaid,
                escooter.model_id,
                escooter_model_type.fee_perminute,
                TIMESTAMPDIFF(SECOND, rental_record.start_time, rental_record.end_time) AS duration,
                CEIL(TIMESTAMPDIFF(SECOND, rental_record.start_time, rental_record.end_time) * escooter_model_type.fee_perminute) AS total_fee
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
                rentalRecord.setRentalRecordId(rs.getInt("rental_record_id"));
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

    /**
     * Reutrn area row mapper
     *
     * @return return area.
     */
    public class ReturnAreaRowMapper implements RowMapper<ReturnArea> {

        @Override
        public ReturnArea mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
            ReturnArea returnArea = new ReturnArea();
            returnArea.setIdreturnArea(rs.getInt("idreturn_area"));

            String value = rs.getString("area_point");
            String valuereal = value.replaceAll("[^0-9 .,]+", "");
            ArrayList<String> polygonList = new ArrayList<>(Arrays.asList(valuereal.split(",")));

            ArrayList<Map<String, String>> areaPointList = new ArrayList<>();
            for (String point : polygonList) {
                String[] latLng = point.trim().split(" ");
                if (latLng.length == 2) {
                    Map<String, String> map = new HashMap<>();
                    map.put("latitude", latLng[0]);
                    map.put("longitude", latLng[1]);
                    areaPointList.add(map);
                }
            }
            returnArea.setAreaPoint(areaPointList);

            return returnArea;
        }
    }
}
