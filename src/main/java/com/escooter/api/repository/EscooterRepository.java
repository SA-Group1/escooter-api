package com.escooter.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;

/**
 * Repository for managing e-scooter data in the database.
 */
@Repository
public class EscooterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Adds a new e-scooter to the database.
     * 
     * @param modelId the model name of the e-scooter
     */
    public boolean addEscooter(String modelId){
        String sql = "INSERT INTO escooter_rental.escooter (model_id) VALUES (?)";
        jdbcTemplate.update(sql, modelId);
        return true;
    }

    /**
     * Updates the GPS coordinates of an e-scooter in the database.
     * 
     * @param escooterId the e-scooter identifier
     * @param gps the GPS coordinates to update
     * @return true if updating is successful, false otherwise
     */
    public boolean updateGps(String escooterId, GPS gps) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_gps_longitude = ?, escooter_gps_latitude = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, gps.getLongitude(), gps.getLatitude(), escooterId);
        return true;
    }


    /**
     * Queries an e-scooter by its identifier from the database.
     * 
     * @param escooterId the e-scooter identifier
     * @return the Escooter object if found, otherwise null
     */
    public Escooter queryEscooterById(String escooterId) {
		String sql = "SELECT * FROM escooter_rental.escooter WHERE escooter_id = ?";
        RowMapper<Escooter> rowMapper = (rs, rowNum) -> {
			Escooter escooter = new Escooter();
            escooter.setEscooterId(rs.getString("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBatteryLevel(rs.getInt("battery_level"));
            escooter.setGPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return escooter;
        };
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, escooterId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
    }

    /**
     * Queries all available e-scooters from the database.
     * 
     * @return a list of available Escooter objects if found, otherwise null
     */
    public List<Escooter> queryEscooters() {
        String sql = "SELECT * FROM escooter_rental.escooter WHERE escooter_status = ?";
        RowMapper<Escooter> rowMapper = (rs, rowNum) -> {
			Escooter escooter = new Escooter();
            escooter.setEscooterId(rs.getString("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBatteryLevel(rs.getInt("battery_level"));
            escooter.setGPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return escooter;
        };
		try {
			return jdbcTemplate.query(sql, rowMapper, "Available");
		} catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Queries all available e-scooters within the specified radius from the database.
     * 
     * @param longitude the longitude coordinate of the center point
     * @param latitude the latitude coordinate of the center point
     * @param distance the maximum distance (in kilometers) from the center point to search for available e-scooters
     * @return a list of available Escooter objects if found, otherwise null
     */
    public List<Escooter> queryAvailableEscooters(double longitude, double latitude, double distance) {
        String sql = 
        """
            SELECT *, 6371 * ACOS (
                COS(RADIANS(?)) * COS(RADIANS(escooter_gps_latitude)) * COS(RADIANS(escooter_gps_longitude) - RADIANS(?)) +
                SIN(RADIANS(?)) * SIN(RADIANS(escooter_gps_latitude))
            ) AS distance
            FROM escooter_rental.escooter
            WHERE escooter_status = ?
            HAVING distance < ?
            ORDER BY distance;
        """;
        RowMapper<Escooter> rowMapper = (rs, rowNum) -> {
			Escooter escooter = new Escooter();
            escooter.setEscooterId(rs.getString("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBatteryLevel(rs.getInt("battery_level"));
            escooter.setGPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return escooter;
        };
		try {
			return jdbcTemplate.query(sql, rowMapper, latitude, longitude, latitude, "Available", distance);
		} catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Updates the GPS coordinates of an e-scooter in the database.
     * 
     * @param escooterId the e-scooter identifier
     * @param gps the GPS coordinates to update
     * @return true if updating is successful, false otherwise
     */
    public boolean updateStatus(String escooterId, String status) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_status = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, status, escooterId);
        return true;
    }

    public boolean updateBetteryLevel(String escooterId, int batteryLevel){
        String sql = "UPDATE escooter_rental.escooter SET battery_level = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, batteryLevel, escooterId);
        return true;
    }
    
    public Escooter queryRentedEscooterByAccount(String account) {
        String sql = "SELECT * FROM escooter_rental.escooter WHERE escooter_id = (SELECT escooter_id FROM escooter_rental.rental_record WHERE user_id = (SELECT user_id FROM escooter_rental.user WHERE account = ?) AND end_time IS NULL)";
        RowMapper<Escooter> rowMapper = (rs, rowNum) -> {
			Escooter escooter = new Escooter();
            escooter.setEscooterId(rs.getString("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBatteryLevel(rs.getInt("battery_level"));
            escooter.setGPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return escooter;
        };
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, account);
		} catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean updateEscooterParkStatusbyId(String escooterId, String status) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_status = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, status, escooterId);
        return true;
    }
}
