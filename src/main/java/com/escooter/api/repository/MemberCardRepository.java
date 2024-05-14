package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
     */
	public void addCard(MemberCard memberCard){
		System.out.println("EXCUTE INSERT MEMBER");
		
		jdbcTemplate.update(String.format("INSERT INTO escooter_rental.member_card (membercard_id,expiration_date) VALUES ('%s','%s')",
		memberCard.getCardNumber(),memberCard.getExpirationDate()));
	}
}