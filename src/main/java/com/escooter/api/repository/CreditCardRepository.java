package com.escooter.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
     *
     * @param creditCard The credit card to add.
     * @return True if adding is successful, false otherwise.
     * @throws DuplicateKeyException Throws when the credit card is already in
     * the database.
     */
    public boolean addCreditCard(CreditCard creditCard) throws DuplicateKeyException {
        System.out.println("EXCUTE INSERT MEMBER");

        String sql = "INSERT INTO escooter_rental.credit_card (creditcard_id,expiration_date,card_holder_name) VALUES (?,?,?)";
        jdbcTemplate.update(sql, creditCard.getCardNumber(), creditCard.getExpirationDate(), creditCard.getCardHolderName());

        return true;
    }

    /**
     * Gets the credit card by account.
     *
     * @param account The user's account.
     * @return The user's credit card.
     */
    public CreditCard getCreditCard(String account) {
        String sql
                = "SELECT creditcard_id, expiration_date, card_holder_name "
                + "FROM escooter_rental.credit_card "
                + "WHERE creditcard_id = (SELECT creditcard_id FROM escooter_rental.user WHERE account = ?)";

        try {
            return jdbcTemplate.queryForObject(sql, new CreditCardRowMapper(), new Object[]{account});
        } catch (EmptyResultDataAccessException e) {
            return new CreditCard();
        }
    }

    /**
     * Binds a credit card to the user.
     *
     * @param account The user's account.
     * @param cardNumber The credit card number.
     * @return True if binding is successful.
     */
    public boolean bindCreditCard(String account, String cardNumber) {
        String sql = "UPDATE `escooter_rental`.`user` SET `creditcard_id` = ? WHERE (`account` = ?)";
        jdbcTemplate.update(sql, cardNumber, account);
        return true;
    }

    /**
     * Unbinds a credit card from the user.
     *
     * @param account The user's account.
     * @return The unbound credit card number.
     */
    public String unbindCreditCard(String account) {

        String sql = "SELECT creditcard_id FROM `escooter_rental`.`user` WHERE (`account` = ?)";

        String cardNumber = jdbcTemplate.queryForObject(sql, String.class, new Object[]{account});

        sql = "UPDATE `escooter_rental`.`user` SET `creditcard_id` = NULL WHERE (`account` = ?)";
        jdbcTemplate.update(sql, account);

        return cardNumber;
    }

    /**
     * Deletes a credit card from the database.
     *
     * @param cardNumber The credit card number.
     * @return True if deletion is successful, false otherwise.
     */
    public boolean deleteCreditCard(String cardNumber) {
        String sql = "DELETE FROM `escooter_rental`.`credit_card` WHERE `creditcard_id` = ?";
        int rowsAffected = jdbcTemplate.update(sql, cardNumber);
        return rowsAffected > 0;
    }

    /**
     * RowMapper implementation for mapping a row in the result set to a
     * CreditCard object.
     */
    private static class CreditCardRowMapper implements RowMapper<CreditCard> {

        /**
         * Maps a row in the result set to a CreditCard object.
         *
         * @param rs The ResultSet to map (pre-initialized for the current row).
         * @param rowNum The number of the current row.
         * @return The mapped CreditCard object.
         * @throws SQLException If an SQLException is encountered getting column
         * values.
         */
        @Override
        public CreditCard mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber(rs.getString("creditcard_id"));
            creditCard.setExpirationDate(rs.getString("expiration_date"));
            creditCard.setCardHolderName(rs.getString("card_holder_name"));
            return creditCard;
        }
    }
}
