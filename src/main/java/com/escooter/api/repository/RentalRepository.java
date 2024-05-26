package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing rental-related data in the database.
 */
@Repository
public class RentalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Checks if a given point (longitude and latitude) is within a designated return area.
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
}
