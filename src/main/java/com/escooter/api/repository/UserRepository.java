package com.escooter.api.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.dto.UserDTO;
import com.escooter.api.model.User;
/**
 * Repository for managing user data in the database.
 */
@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
     * Queries the user by account from the database.
     * 
     * @param account the account identifier
     * @return the User object if found, otherwise null
     */
	public User queryUserByAccount(String account) {
		String sql = "SELECT * FROM escooter_rental.user WHERE account = ?";
        RowMapper<User> rowMapper = (rs, rowNum) -> {
			User user = new User();
            user.setAccount(rs.getString("account"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
			System.out.println(rs.getTimestamp("registration_time").toLocalDateTime());
            user.setRegistrationTime(rs.getTimestamp("registration_time").toLocalDateTime().toString());
            return user;
        };
		User user;
		try {
			user = jdbcTemplate.queryForObject(sql, rowMapper, account);
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}
        return user;
	}
	/**
     * Adds a new User to the database.
     */
	public boolean createUser(String account, String password, String userName, String email) {
		String sql = "INSERT INTO escooter_rental.user (account, password, username, email, registration_time) VALUES (?, ?, ?, ?, NOW())";
		jdbcTemplate.update(sql, account, password, userName, email);
		return true;
	}

	public boolean updateUserData(UserDTO userDTO) {
		String sql = "UPDATE escooter_rental.user SET password = ?, email = ?, username = ?, phone_number = ? WHERE account = ?";
		jdbcTemplate.update(sql, userDTO.getPassword(), userDTO.getEmail(), userDTO.getUserName(), userDTO.getPhoneNumber(), userDTO.getAccount());
		return true;
	}


	// public void addUser(User user) {
	// 	System.out.println("新增使用者成功");
	// 	jdbcTemplate.update("INSERT INTO escooter_rental.user (account, password, registration_time, username) VALUES (?, ?, NOW(), ?)",
	// 							user.getAccount(), user.getPassword(), user.getUserName());
	// }
}