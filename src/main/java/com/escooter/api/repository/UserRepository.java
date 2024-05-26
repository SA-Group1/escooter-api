package com.escooter.api.repository;


import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.CreditCard;
import com.escooter.api.model.MemberCard;
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
		String sql = """
			SELECT
				user.*,
				credit_card.expiration_date AS credit_card_expiration_date,
				credit_card.card_holder_name,
				member_card.expiration_date AS member_card_expiration_date
			FROM escooter_rental.user
			LEFT JOIN credit_card ON user.creditcard_id = credit_card.creditcard_id
			LEFT JOIN member_card ON user.membercard_id = member_card.membercard_id
			WHERE account = ?
		""";
        RowMapper<User> rowMapper = (rs, rowNum) -> {
			User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setAccount(rs.getString("account"));
            user.setUserName(rs.getString("username"));
            user.setEmail(rs.getString("email"));
			user.setPhoneNumber(rs.getString("phone_number"));
			System.out.println(rs.getTimestamp("registration_time").toLocalDateTime());
            user.setRegistrationTime(rs.getTimestamp("registration_time").toLocalDateTime().toString());
			CreditCard creditCard = new CreditCard(rs.getString("creditcard_id"),
												   rs.getString("credit_card_expiration_date"),
												   rs.getString("card_holder_name"));
			if (creditCard.getCardNumber() != null) {
				creditCard.setCardNumber(creditCard.getCardNumber().substring(12, 16));
			}
			user.setCreditCard(creditCard);
			MemberCard memberCard = new MemberCard(rs.getString("membercard_id"), rs.getString("member_card_expiration_date"));
			user.setMemberCard(memberCard);
			//user.setImage(rs.getBlob("user_photo").tobyte());
			Blob blob = rs.getBlob("user_photo");
			if (blob != null) {
				user.setImage(blob.getBytes(1, (int) blob.length()));
			}
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
     * Queries the user by account from the database.
     * 
     * @param account the account identifier
     * @return the User object if found, otherwise null
     */
	public int queryUserIdByAccount(String account) {
		String sql = """
			SELECT
				user.user_id,
			FROM escooter_rental.user
			WHERE account = ?
		""";
        return jdbcTemplate.queryForObject(sql,int.class, new Object[]{account});
	}

	/**
     * Adds a new User to the database.
     * 
     * @param account the user's account identifier
     * @param password the user's password
     * @param userName the user's name
     * @param email the user's email
	 * @param phoneNumber the user's phone number
     * @return True if adding is successful, false otherwise.
     */
	public boolean createUser(String account, String password, String userName, String email, String phoneNumber) {
		String sql = "INSERT INTO escooter_rental.user (account, password, username, email, registration_time, phone_number) VALUES (?, ?, ?, ?, NOW(), ?)";
		jdbcTemplate.update(sql, account, password, userName, email, phoneNumber);
		return true;
	}

	/**
     * Updates user data in the database.
     * 
     * @param userDTO the user data transfer object containing updated user information
     * @return True if updating is successful, false otherwise.
     */
	public boolean updateUserData(User user) {
		String sql = "UPDATE escooter_rental.user SET  email = ?, username = ?, phone_number = ? WHERE account = ?";
		jdbcTemplate.update(sql, user.getEmail(), user.getUserName(), user.getPhoneNumber(), user.getAccount());
		return true;
	}


	public boolean uploadUserPhoto(String account, byte[]image) {
        String sql = "UPDATE user SET user_photo = ? WHERE account = ?";
        jdbcTemplate.update(sql, image, account);
		return true;
    }
}