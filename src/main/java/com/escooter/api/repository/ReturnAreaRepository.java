package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnAreaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public boolean checkWithinReturnArea(double longitude, double latitude) {
        String sql = "SELECT COUNT(*) FROM escooter_rental.return_area WHERE ST_WITHIN(ST_GeomFromText(CONCAT('POINT(', ?, ' ', ?, ')')), area_point)";
        try {
            int res = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{latitude, longitude});
            return res > 0 ? true : false;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
