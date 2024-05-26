package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.repository.CreditCardRepository;

/**
 * Service class for managing credit card operations.
 */
@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    /**
     * Binds a credit card to a user account.
     *
     * @param account     The user account.
     * @param creditCard  The credit card to bind.
     * @param cvv         The CVV of the credit card.
     * @return True if binding is successful.
     * @throws CardExpiredException   If the credit card is expired.
     * @throws CreditCardCvvException If the credit card CVV is invalid.
     */
    public boolean bindCreditCard(String account, CreditCard creditCard , String cvv) throws CardExpiredException, CreditCardCvvException {

        if(!isCreditCardNotExpired(creditCard.getExpirationDate())) {
            throw new CardExpiredException("Invalid card Expired.");
        }

        if(!isCreditCardVaild(creditCard , cvv)) {
            throw new CreditCardCvvException("Invalid card cvv.");
        }

        creditCardRepository.addCreditCard(creditCard);
        creditCardRepository.bindCreditCard(account,creditCard.getCardNumber());

		return true;
	}

    /**
     * Unbinds a credit card from a user account.
     *
     * @param account The user account.
     * @return True if unbinding is successful.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
	public boolean unbindCreditCard(String account) throws UserCredentialsException {

		String cardNumber = creditCardRepository.unbindCreditCard(account);
        creditCardRepository.deleteCreditCard(cardNumber);

		return true;
	}

    /**
     * Retrieves the credit card of a user.
     *
     * @param account The user account.
     * @return The user's credit card.
     */
	public CreditCard getCreditCard(String account){
		return creditCardRepository.getCreditCard(account);
	}

    /**
     * Validates a credit card and its CVV.
     *
     * @param creditCard The credit card to validate.
     * @param cvv        The CVV of the credit card.
     * @return True if the credit card is valid.
     */
    private boolean isCreditCardVaild(CreditCard creditCard , String vcc){
        // TODO add credit card vaild
        return true;
    }

    /**
     * Checks if the credit card is not expired.
     *
     * @param expirationDate The expiration date of the credit card.
     * @return True if the credit card is not expired.
     */
    private  boolean isCreditCardNotExpired(String expirationDate){
        YearMonth yearMonth = YearMonth.parse(expirationDate,DateTimeFormatter.ofPattern("MMyy"));
        return !LocalDate.now().isAfter(yearMonth.atDay(1));
    }
}
