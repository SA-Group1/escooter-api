package com.escooter.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.MemberCard;



/**
 * Repository for managing member card data in the database.
 */
@Repository
public class MemberCardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Adds a new member card to the database.
	 * @param memberCard The member card to add.
	 * @return True if adding is successful, false otherwise.
	 */
	public boolean addMemberCard(MemberCard memberCard) {
		System.out.println("EXCUTE INSERT MEMBER");
		
		jdbcTemplate.update(String.format("INSERT INTO escooter_rental.member_card (membercard_id,expiration_date) VALUES ('%s','%s')",
		memberCard.getCardNumber(),memberCard.getExpirationDate()));
		return true;
	}

	/**
	 * Binds a member card to the user.
	 * 
	 * @param account the user's account.
	 * @param cardNumber the member card number.
	 * @return True if binding is successful.
	 */
	public boolean bindMemberCard(String account, String cardNumber) {
		String sql = "UPDATE `escooter_rental`.`user` SET `membercard_id` = ? WHERE (`account` = ?)";
		jdbcTemplate.update(sql, cardNumber, account);
		return true;
	}

	/**
	 * Unbinds a credit card for the user.
	 * 
	 * @param account the user's account.
	 * @return True if unbinding is successful.
	 */
	public String unbindMemberCard(String account) {

		String sql = "SELECT membercard_id FROM `escooter_rental`.`user` WHERE (`account` = ?)";

		String cardNumber = jdbcTemplate.queryForObject(sql,String.class, new Object[]{account});

		sql = "UPDATE `escooter_rental`.`user` SET `membercard_id` = NULL WHERE (`account` = ?)";
		jdbcTemplate.update(sql, account);

		return cardNumber;
	}

	public boolean deleteMemberCard(String cardNumber){
		String sql = "DELETE FROM `escooter_rental`.`member_card` WHERE `membercard_id` = ?";
		int rowsAffected = jdbcTemplate.update(sql, cardNumber);
		return rowsAffected > 0;
	}

	/**
	 * Gets the member card by account.
	 * @param account The user's account.
	 * @return The user's member card.
	 */
	public MemberCard getMemberCard(String account) {
        String sql = 
        "SELECT membercard_id, expiration_date " +
        "FROM escooter_rental.member_card " +
        "WHERE membercard_id = (SELECT membercard_id FROM escooter_rental.user WHERE account = ?)";
        try {
            return jdbcTemplate.queryForObject(sql,new MemberCardRowMapper(),new Object[]{account});
        } catch (EmptyResultDataAccessException e) {
            return new MemberCard();
        }
    }

	private static class MemberCardRowMapper implements RowMapper<MemberCard> {

		/**
		 * The map for construct a member card object using data gets from database.
		 * @param rs For selecting column.
		 * @param rowNum For selecting row.
		 * @return a member card object.
		 */
		@Override
		public MemberCard mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
			MemberCard memberCard = new MemberCard();
			memberCard.setCardNumber(rs.getString("membercard_id"));
			memberCard.setExpirationDate(rs.getString("expiration_date"));
			return memberCard;
		}
	}
}