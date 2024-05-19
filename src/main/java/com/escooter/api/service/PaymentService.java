package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.User;
import com.escooter.api.repository.UserRepository;
import com.escooter.api.model.CreditCard;
import com.escooter.api.repository.CreditCardRepository;
import com.escooter.api.model.MemberCard;
import com.escooter.api.repository.MemberCardRepository;
import com.escooter.api.exceptions.CardExpirationException;


/**
 * Service class for managing payment related requests.
 */
@Service
public class PaymentService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private MemberCardRepository memberCardRepository;

	/**
     * Adds a new credit card if it is valid.
     * @param creditCard The credit card to add.
     * @param cvv The CVV (Card Verification Value) of the credit card.
     * @return True if the credit card is valid and added successfully, false otherwise.
	 * @throws CardExpirationExpection Throws when it passes its expiration date.
	 * @throws DateTimeParseException Throws when the expiration date is in the wrong format.
     */
	public boolean addCreditCard(CreditCard creditCard , String cvv) throws CardExpirationException {

		// check if the card is expiration or not.
		YearMonth expirationDate = YearMonth.parse(creditCard.getExpirationDate(),DateTimeFormatter.ofPattern("MMyy"));
		if(LocalDate.now().isAfter(expirationDate.atDay(1))) {
			throw new CardExpirationException();
		}

		// vaild the credit card with cvv.
		if(!creditCard.isVaild(cvv)) {
			return false;
		}
		
		// add credit card to the repository.
		creditCardRepository.addCard(creditCard);
		return true;
	}

	/**
	 * Bind a credit card to the user.
	 * @param user The user who wants to bind a credit card.
	 * @param creditCard The credit card to bind.
	 * @return True if binding is successful.
	 */
	public boolean bindCreditCard(User user, CreditCard creditCard) {
		userRepository.bindCreditCard(user.getAccount(),creditCard.getCardNumber());
		return true;
	}

	/**
	 * Unbinds a credit card for the user.
	 * @param user The user who wants to unbind a credit card.
	 * @return True if unbinding is successful.
	 */
	public boolean unbindCreditCard(User user) {
		userRepository.unbindCreditCard(user.getAccount());
		return true;
	}

	/**
	 * Adds a new member card if it is valid.
	 * @param memberCard The member card to add.
	 * @return True if the member card is valid and added successfully, false otherwise.
	 */
	public boolean addMemberCard(MemberCard memberCard) throws CardExpirationException {

		// check if the card is expiration or not.
		YearMonth expirationDate = YearMonth.parse(memberCard.getExpirationDate(),DateTimeFormatter.ofPattern("MMyy"));
		if(LocalDate.now().isAfter(expirationDate.atDay(1))) {
			throw new CardExpirationException();
		}
		
		// vaild the member card.
		if(!memberCard.isVaild()) {
			return false;
		}
		
		// add member card to the repository.
		memberCardRepository.addCard(memberCard);
		return true;
	}

	/**
	 * Bind a member card to the user.
	 * @param user The user who wants to bind a member card.
	 * @param memberCard The member card to bind.
	 * @return True if binding is successful.
	 */
	public boolean bindMemberCard(User user, MemberCard memberCard) {
		userRepository.bindMemberCard(user.getAccount(),memberCard.getCardNumber());
		return true;
	}

	/**
	 * Unbinds a member card for the user.
	 * @param user The user who wants to unbind a member card.
	 * @return True if unbinding is successful.
	 */
	public boolean unbindMemberCard(User user) {
		userRepository.unbindMemberCard(user.getAccount());
		return true;
	}

	/**
	 * get the credit card from the user.
	 * @param user The user who wants to get their credit card.
	 * @return The user's credit card.
	 */
	public CreditCard getCreditCard(User user) {
		return creditCardRepository.getCreditCard(user.getAccount());
	}

	/**
	 * get the member card from the user.
	 * @param user The user who wants to get their member card.
	 * @return The user's member card.
	 */
	public MemberCard getMemberCard(User user) {
		return memberCardRepository.getMemberCard(user.getAccount());
	}
}
