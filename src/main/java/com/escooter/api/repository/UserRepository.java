package com.escooter.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User queryUserByAccount(String account) {
		String sql = "SELECT * FROM escooter_rental.user WHERE account = ?";
        RowMapper<User> rowMapper = (rs, rowNum) -> {
			User user = new User();
            user.setAccount(rs.getString("account"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
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

	public boolean createUser(String account, String userName, String password) {
		String sql = "INSERT INTO escooter_rental.user (account, password, registration_time, username) VALUES (?, ?, NOW(), ?)";
			jdbcTemplate.update(sql, account, userName, password);
		return true;
	}

	// public void addUser(User user) {
	// 	System.out.println("新增使用者成功");
	// 	jdbcTemplate.update("INSERT INTO escooter_rental.user (account, password, registration_time, username) VALUES (?, ?, NOW(), ?)",
	// 							user.getAccount(), user.getPassword(), user.getUserName());
	// }
}