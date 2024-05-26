package com.escooter.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.CreditCard;

/**
 * Repository for managing credit card data in the database.
 */
@Repository
public class CreditCardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Adds a new credit card to the database.
	 * @param creditCard The credit card to add.
	 * @return True if adding is successful, false otherwise.
	 *  @throws DuplicateKeyException Throws when the credit card is already in the database.
	 */
	public boolean addCreditCard(CreditCard creditCard) throws DuplicateKeyException {
		System.out.println("EXCUTE INSERT MEMBER");
		
		String sql = "INSERT INTO escooter_rental.credit_card (creditcard_id,expiration_date,card_holder_name) VALUES (?,?,?)";
		jdbcTemplate.update(sql,creditCard.getCardNumber(),creditCard.getExpirationDate(),creditCard.getCardHolderName());
	
		return true;
	}

	/**
	 * Gets the credit card by account.
	 * @param account The user's account.
	 * @return The user's credit card.
	 */
	public CreditCard getCreditCard(String account) {
		String sql = 
		"SELECT creditcard_id, expiration_date, card_holder_name " +
		"FROM escooter_rental.credit_card " +
		"WHERE creditcard_id = (SELECT creditcard_id FROM escooter_rental.user WHERE account = ?)";
		CreditCard creditCard = jdbcTemplate.queryForObject(sql,new CreditCardRowMapper(),new Object[]{account});
		return creditCard;
	}

	/**
	 * Binds a credit card to the user.
	 * 
	 * @param account the user's account.
	 * @param cardNumber the credit card number.
	 * @return True if binding is successful.
	 */
	public boolean bindCreditCard(String account, String cardNumber) {
		String sql = "UPDATE `escooter_rental`.`user` SET `creditcard_id` = ? WHERE (`account` = ?)";
		jdbcTemplate.update(sql, cardNumber, account);
		return true;
	}

	/**
	 * Unbinds a credit card for the user.
	 * 
	 * @param account the user's account.
	 * @return True if unbinding is successful.
	 */
	public String unbindCreditCard(String account) {

		String sql = "SELECT creditcard_id FROM `escooter_rental`.`user` WHERE (`account` = ?)";

		String cardNumber = jdbcTemplate.queryForObject(sql,String.class, new Object[]{account});

		sql = "UPDATE `escooter_rental`.`user` SET `creditcard_id` = NULL WHERE (`account` = ?)";
		jdbcTemplate.update(sql, account);

		return cardNumber;
	}

	public boolean deleteCreditCard(String cardNumber){
		String sql = "DELETE FROM `escooter_rental`.`credit_card` WHERE `creditcard_id` = ?";
		int rowsAffected = jdbcTemplate.update(sql, cardNumber);
		return rowsAffected > 0;
	}


	private static class CreditCardRowMapper implements RowMapper<CreditCard> {

		/**
		 * The map for construct a credit card object using data gets from database.
		 * @param rs For selecting column.
		 * @param rowNum For selecting row.
		 * @return a credit card object.
		 */		
		@Override
		public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
			CreditCard creditCard = new CreditCard();
			creditCard.setCardNumber(rs.getString("creditcard_id"));
			creditCard.setExpirationDate(rs.getString("expiration_date"));
			creditCard.setCardHolderName(rs.getString("card_holder_name"));
			return creditCard;
		}
	}
}
