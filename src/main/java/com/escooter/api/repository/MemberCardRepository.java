package com.escooter.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
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
     */
	public void addCard(MemberCard memberCard) {
		System.out.println("EXCUTE INSERT MEMBER");
		
		jdbcTemplate.update(String.format("INSERT INTO escooter_rental.member_card (membercard_id,expiration_date) VALUES ('%s','%s')",
		memberCard.getCardNumber(),memberCard.getExpirationDate()));
	}

	public MemberCard getMemberCard(String account) {
		String sql = 
		"SELECT membercard_id, expiration_date " +
		"FROM escooter_rental.member_card " +
		"WHERE membercard_id = (SELECT membercard_id FROM escooter_rental.user WHERE account = ?)";
		MemberCard memberCard = jdbcTemplate.queryForObject(sql,new MemberCardRowMapper(),new Object[]{account});
		return memberCard;
	}

	private static class MemberCardRowMapper implements RowMapper<MemberCard> {
		@Override
		public MemberCard mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberCard memberCard = new MemberCard();
			memberCard.setCardNumber(rs.getString("membercard_id"));
			memberCard.setExpirationDate(rs.getString("expiration_date"));
			return memberCard;
		}
	}
}