package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.CreditCard;

@Repository
public class CreditCardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCard(CreditCard creditCard){
		System.out.println("EXCUTE INSERT MEMBER");
		
		jdbcTemplate.update(String.format("INSERT INTO escooter_rental.credit_card (creditcard_id,expiration_date) VALUES ('%s','%s')",
		creditCard.getCardNumber(),creditCard.getExpirationDate()));
		/*
		
		jdbcTemplate.update("INSERT INTO escooter_rental.credit_card (creditcard_id)"
	  		+ "VALUES (?,now())",creditCardModel.getCreditCard_ID());
		 */

	}
}
