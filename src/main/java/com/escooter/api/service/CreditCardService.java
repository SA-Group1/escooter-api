package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.CreditCard;
import com.escooter.api.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
    CreditCardRepository creditCardRepository;
	public void addCard(CreditCard creditCardModel){
		creditCardRepository.addCard(creditCardModel);
	}
}
