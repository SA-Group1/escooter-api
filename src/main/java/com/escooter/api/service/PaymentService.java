package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.MemberCard;
import com.escooter.api.repository.MemberCardRepository;

/**
 * Service class for managing payment related requests.
 */
@Service
public class PaymentService {

	@Autowired
	private MemberCardRepository memberCardRepository;

	/**
     * Adds a new member card if it is valid.
     * @param memberCard The member card to add.
     * @return True if the member card is valid and added successfully, false otherwise.
     */
	public boolean addCard(MemberCard memberCard){

		if(!memberCard.isVaild()){
			return false;
		}

		memberCardRepository.addCard(memberCard);
		return true;
	}
}
