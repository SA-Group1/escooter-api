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
     * @param escooterId the escooter id of the e-scooter.
     * @param modelId the model name of the e-scooter.
     * @return true if adding the e-scooter is successful.
     */
    public boolean addEscooter(String escooterId, String modelId) {
        String sql = "INSERT INTO escooter_rental.escooter (escooter_id , model_id) VALUES (? , ?)";
        jdbcTemplate.update(sql, escooterId, modelId);
        return true;
    }

    /**
     * Updates the GPS coordinates of an e-scooter in the database.
     *
     * @param escooterId the e-scooter identifier.
     * @param gps the GPS coordinates to update.
     * @return true if updating is successful, false otherwise.
     */
    public boolean updateGps(String escooterId, GPS gps) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_gps_longitude = ?, escooter_gps_latitude = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, gps.getLongitude(), gps.getLatitude(), escooterId);
        return true;
    }

    /**
     * Queries an e-scooter by its identifier from the database.
     *
     * @param escooterId the e-scooter identifier.
     * @return the Escooter object if found, otherwise null.
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
     * @return a list of all Escooter objects if found, otherwise null.
     */
    public List<String> queryEscooters() {
        String sql = "SELECT escooter_id FROM escooter_rental.escooter";
        RowMapper<String> rowMapper = (rs, rowNum) -> {
            return rs.getString("escooter_id");
        };
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Queries all available e-scooters within the specified radius from the
     * database.
     *
     * @param gps the GPS coordinates of the center point.
     * @param distance the maximum distance (in kilometers) from the center
     * point to search for available e-scooters.
     * @return a list of available Escooter objects if found, otherwise null.
     */
    public List<Escooter> queryAvailableEscooters(GPS gps, double distance) {
        String sql = """
            SELECT escooter.*, escooter_model_type.fee_perminute, 6371 * ACOS (
                COS(RADIANS(?)) * COS(RADIANS(escooter_gps_latitude)) * COS(RADIANS(escooter_gps_longitude) - RADIANS(?)) +
                SIN(RADIANS(?)) * SIN(RADIANS(escooter_gps_latitude))
            ) AS distance
            FROM escooter_rental.escooter
            JOIN escooter_rental.escooter_model_type
            ON escooter_model_type.model_id = escooter.model_id
            WHERE escooter_status = ?
            HAVING distance < ?
            ORDER BY distance;
        """;
        RowMapper<Escooter> rowMapper = (rs, rowNum) -> {
            Escooter escooter = new Escooter();
            escooter.setEscooterId(rs.getString("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setFeePerMinutes(rs.getDouble("fee_perminute"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBatteryLevel(rs.getInt("battery_level"));
            escooter.setGPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return escooter;
        };
        try {
            return jdbcTemplate.query(sql, rowMapper, gps.getLatitude(), gps.getLongitude(), gps.getLatitude(), "Available", distance);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Updates the status of an e-scooter in the database.
     *
     * @param escooterId the e-scooter identifier.
     * @param status the new status to update.
     * @return true if updating is successful, false otherwise.
     */
    public boolean updateStatus(String escooterId, String status) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_status = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, status, escooterId);
        return true;
    }

    /**
     * Updates the battery level of the specified e-scooter.
     *
     * @param escooterId the ID of the e-scooter to update.
     * @param batteryLevel the new battery level.
     * @return true if the update was successful.
     */
    public boolean updateBatteryLevel(String escooterId, int batteryLevel) {
        String sql = "UPDATE escooter_rental.escooter SET battery_level = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, batteryLevel, escooterId);
        return true;
    }

    /**
     * Queries the currently rented e-scooter for a given account.
     *
     * @param account the account identifier.
     * @return the rented e-scooter, or null if no e-scooter is rented by the
     * account.
     */
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

    /**
     * Updates the parking status of the specified e-scooter.
     *
     * @param escooterId the ID of the e-scooter to update.
     * @param status the new parking status.
     * @return true if the update was successful.
     */
    public boolean updateEscooterParkStatusbyId(String escooterId, String status) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_status = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, status, escooterId);
        return true;
    }

    /**
     * Retrieves the GPS coordinates of an e-scooter by its identifier.
     *
     * @param escooterId the e-scooter identifier.
     * @return the GPS coordinates if found, otherwise null.
     */
    public GPS getEscooterGpsById(String escooterId) {
        String sql = "SELECT escooter_gps_longitude , escooter_gps_latitude FROM escooter_rental.escooter WHERE escooter_id = ?";
        RowMapper<GPS> rowMapper = (rs, rowNum) -> {
            GPS gps = new GPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return gps;
        };
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, escooterId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
