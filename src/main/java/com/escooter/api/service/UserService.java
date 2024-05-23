package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.model.MemberCard;
import com.escooter.api.model.User;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.CreditCardRepository;
import com.escooter.api.repository.MemberCardRepository;
import com.escooter.api.repository.UserRepository;

public class UserService {

    @Autowired
	private UserRepository userRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private MemberCardRepository memberCardRepository;
    @Autowired
    private CreditCardService creditCardService;

    /**
	 * Bind a credit card to the user.
	 * @param userCredentials The user who wants to bind a credit card.
	 * @param creditCard The credit card to bind.
	 * @param cvv The CVV (Card Verification Value) of the credit card.
	 * @return True if binding is successful.
     * @throws UserCredentialsException
     * @throws CardExpiredException 
     * @throws CreditCardCvvException 
	 */
	public boolean bindCreditCard(UserCredentials userCredentials, CreditCard creditCard , String cvv) throws UserCredentialsException, CardExpiredException, CreditCardCvvException {

		if(!verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid username or password.");
		}

        try {
            creditCardService.addCreditCard(creditCard, cvv);
            userRepository.bindCreditCard(userCredentials.getAccount(),creditCard.getCardNumber());
        } catch (CardExpiredException cardExpiredException) {
            throw new CardExpiredException("Invalid card Expired.");
        } catch (CreditCardCvvException creditCardCvvException){
            throw new CreditCardCvvException("Invalid card cvv.");
        }
		return true;
	}
    
    public boolean verifyUserCredentials(UserCredentials userCredentials){
        // todo
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
     * @throws CardExpiredException
	 */
	public boolean addMemberCard(MemberCard memberCard) throws CardExpiredException {

		YearMonth expirationDate = YearMonth.parse(memberCard.getExpirationDate(),DateTimeFormatter.ofPattern("MMyy"));
		if(LocalDate.now().isAfter(expirationDate.atDay(1))) {
			throw new CardExpiredException("Invalid card expiration.");
		}
		
		if(!memberCard.isVaild()) {
			return false;
		}
		
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
