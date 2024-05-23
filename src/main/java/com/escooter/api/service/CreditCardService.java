package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.model.CreditCard;

public class CreditCardService {
    public boolean addCreditCard(CreditCard creditCard , String vcc) throws CardExpiredException , CreditCardCvvException{
		
		if(!isCreditCardExpiration(creditCard.getExpirationDate())) {
			throw new CardExpiredException("Invalid card Expired.");
		}

		if(!isCreditCardVaild(creditCard , vcc)) {
			throw new CreditCardCvvException("Invalid card cvv.");
		}

        return true;
    }

    private boolean isCreditCardVaild(CreditCard creditCard , String vcc){
        // TODO add credit card vaild
        return true;
    }

    private  boolean isCreditCardExpiration(String expirationDate){
        YearMonth expirationDate = YearMonth.parse(expirationDate,DateTimeFormatter.ofPattern("MMyy"));
        return !LocalDate.now().isAfter(expirationDate.atDay(1));
    }
}
