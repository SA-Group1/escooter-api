package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.model.CreditCard;

public class CreditCardService {
    public boolean addCreditCard(CreditCard creditCard , String cvv) throws CardExpiredException , CreditCardCvvException{
		
		if(!isCreditCardNotExpired(creditCard.getExpirationDate())) {
			throw new CardExpiredException("Invalid card Expired.");
		}

		if(!isCreditCardVaild(creditCard , cvv)) {
			throw new CreditCardCvvException("Invalid card cvv.");
		}

        return true;
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
