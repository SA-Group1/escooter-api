package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addUser(User user) {
		System.out.println("新增使用者成功");
		jdbcTemplate.update("INSERT INTO escooter_rental.user (account, password, registration_time, username) VALUES (?, ?, NOW(), ?)",
								user.getAccount(), user.getPassword(), user.getuserName());
	}
}
