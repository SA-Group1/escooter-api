package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.CreditCard;
import com.escooter.api.repository.CreditCardRepository;
import com.escooter.api.model.MemberCard;
import com.escooter.api.repository.MemberCardRepository;

/**
 * Service class for managing payment related requests.
 */
@Service
public class PaymentService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private MemberCardRepository memberCardRepository;

	/**
     * Adds a new credit card if it is valid.
     * @param creditCard The credit card to add.
     * @param cvv The CVV (Card Verification Value) of the credit card.
     * @return True if the credit card is valid and added successfully, false otherwise.
     */
	public boolean bindCreditCard(CreditCard creditCard , String cvv){

		if(!creditCard.isVaild(cvv)){
			return false;
		}

		creditCardRepository.addCard(creditCard);
		return true;
	}

	/**
     * Adds a new member card if it is valid.
     * @param memberCard The member card to add.
     * @return True if the member card is valid and added successfully, false otherwise.
     */
	public boolean bindMemberCard(MemberCard memberCard){

		if(!memberCard.isVaild()){
			return false;
		}

		memberCardRepository.addCard(memberCard);
		return true;
	}
}
