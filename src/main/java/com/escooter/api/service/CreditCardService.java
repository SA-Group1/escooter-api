package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.CreditCard;
import com.escooter.api.repository.CreditCardRepository;

/**
 * Service class for managing credit cards.
 */
@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
     * Adds a new credit card if it is valid.
     * @param creditCard The credit card to add.
     * @param cvv The CVV (Card Verification Value) of the credit card.
     * @return True if the credit card is valid and added successfully, false otherwise.
     */
	public boolean addCard(CreditCard creditCard , String cvv){

		if(!creditCard.isVaild(cvv)){
			return false;
		}

		creditCardRepository.addCard(creditCard);
		return true;
	}
}
