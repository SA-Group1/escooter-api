package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing user credential verification in the database.
 */
@Repository
public class UserCredentialRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Verifies user credentials.
     *
     * @param account The user's account.
     * @param password The user's password.
     * @return true if the credentials are valid, false otherwise.
     */
    public boolean verifyUserCredentials(String account, String password) {
        String sql = "SELECT COUNT(*) FROM escooter_rental.user WHERE (account = ? AND password = ?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{account, password});
        return count != null && count > 0;
    }
}
