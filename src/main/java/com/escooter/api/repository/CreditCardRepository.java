package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
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
	 * @throws DuplicateKeyException Throws when the credit card is already in the database.
     */
	public boolean addCard(CreditCard creditCard) throws DuplicateKeyException {
		System.out.println("EXCUTE INSERT MEMBER");
		
		String sql = "INSERT INTO escooter_rental.credit_card (creditcard_id,expiration_date,card_holder_name) VALUES (?,?,?)";
		jdbcTemplate.update(sql,creditCard.getCardNumber(),creditCard.getExpirationDate(),creditCard.getCardHolderName());
	
		return true;
	}
}
