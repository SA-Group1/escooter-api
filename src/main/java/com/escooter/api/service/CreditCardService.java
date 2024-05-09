package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.CreditCardModel;
import com.escooter.api.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
    CreditCardRepository creditCardRepository;
	public void addCard(CreditCardModel creditCardModel){
		creditCardRepository.addCard(creditCardModel);
	}
}
