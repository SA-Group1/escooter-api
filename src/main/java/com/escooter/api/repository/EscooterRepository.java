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
     * @param escooterId the e-scooter identifier
     * @param modelName the model name of the e-scooter
     * @param status the status of the e-scooter
     */
    public void addEscooter(int escooterId,String modelName,String status){
        System.out.println("EXCUTE INSERT MEMBER");

        jdbcTemplate.update(String.format("INSERT INTO escooter_rental.escooter (escooter_id,model_id,status) VALUES ('%d','%s','%s')",
        escooterId,modelName,status));
    }

    /**
     * Updates the GPS coordinates of an e-scooter in the database.
     * 
     * @param escooterId the e-scooter identifier
     * @param gps the GPS coordinates to update
     * @return true if updating is successful, false otherwise
     */
    public boolean updateGps(int escooterId, GPS gps) {
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
            escooter.setEscooterId(rs.getInt("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBetteryLevel(rs.getInt("battert_level"));
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
            escooter.setEscooterId(rs.getInt("escooter_id"));
            escooter.setModelId(rs.getString("model_id"));
            escooter.setStatus(rs.getString("escooter_status"));
            escooter.setBetteryLevel(rs.getInt("battert_level"));
            escooter.setGPS(rs.getDouble("escooter_gps_longitude"), rs.getDouble("escooter_gps_latitude"));
            return escooter;
        };
		try {
			return jdbcTemplate.query(sql, rowMapper, "Available");
		} catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
