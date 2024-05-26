package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.repository.CreditCardRepository;

public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

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
	 * Unbinds a credit card for the user.
	 * @param account The user account.
	 * @return True if unbinding is successful.
     * @throws com.escooter.api.exceptions.UserCredentialsException
	 */
	public boolean unbindCreditCard(String account) throws UserCredentialsException {

		String cardNumber = creditCardRepository.unbindCreditCard(account);
        creditCardRepository.deleteCreditCard(cardNumber);

		return true;
	}

    /**
	 * get the credit card from the user.
	 * @param account The user who wants to get their credit card.
	 * @return The user's credit card.
	 */
	public CreditCard getCreditCard(String account){
		return creditCardRepository.getCreditCard(account);
	}

    private boolean isCreditCardVaild(CreditCard creditCard , String vcc){
        // TODO add credit card vaild
        return true;
    }

    private  boolean isCreditCardNotExpired(String expirationDate){
        YearMonth yearMonth = YearMonth.parse(expirationDate,DateTimeFormatter.ofPattern("MMyy"));
        return !LocalDate.now().isAfter(yearMonth.atDay(1));
    }
}
