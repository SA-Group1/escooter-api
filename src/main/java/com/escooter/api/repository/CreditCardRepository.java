package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.CreditCardModel;

@Repository
public class CreditCardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCard(CreditCardModel creditCardModel){
        System.out.println("EXCUTE INSERT MEMBER");
		jdbcTemplate.update("INSERT INTO escooter_rental.credit_card (creditcard_id) VALUES (10)");
		/*
		
		jdbcTemplate.update("INSERT INTO escooter_rental.credit_card (creditcard_id)"
	  		+ "VALUES (?,now())",creditCardModel.getCreditCard_ID());
		 */
	    
  }
}
