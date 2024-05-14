package com.escooter.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;

@Repository
public class EscooterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addEscooter(int escooterId,String modelName,String status){
        System.out.println("EXCUTE INSERT MEMBER");

        jdbcTemplate.update(String.format("INSERT INTO escooter_rental.escooter (escooter_id,model_id,status) VALUES ('%d','%s','%s')",
        escooterId,modelName,status));
    }

    public boolean updateGps(int escooterId, GPS gps) {
        String sql = "UPDATE escooter_rental.escooter SET escooter_gps_longitude = ?, escooter_gps_latitude = ? WHERE escooter_id = ?";
        jdbcTemplate.update(sql, gps.getLongitude(), gps.getLatitude(), escooterId);
        return true;
    }

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
