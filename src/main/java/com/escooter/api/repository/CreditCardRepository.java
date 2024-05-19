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
	public boolean addCard(CreditCard creditCard) throws DuplicateKeyException {
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
