package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
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
     */
	public void addCard(CreditCard creditCard){
		System.out.println("EXCUTE INSERT MEMBER");
		
		jdbcTemplate.update(String.format("INSERT INTO escooter_rental.credit_card (creditcard_id,expiration_date,card_holder_name) VALUES ('%s','%s','%s')",
		creditCard.getCardNumber(),creditCard.getExpirationDate(),creditCard.getCardHolderName()));
	}
}
