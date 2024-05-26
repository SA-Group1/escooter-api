package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.model.MemberCard;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.UserRepository;

@Service
public class UserPaymentService {


    @Autowired
    private CreditCardService creditCardService;
	@Autowired
    private MemberCardService memberCardService;
	@Autowired
	private UserCredentialService userCredentialService;
	@Autowired
	private UserRepository userRepository;

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

		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        try {
            creditCardService.bindCreditCard(userCredentials.getAccount(),creditCard,cvv);
        } catch (CardExpiredException cardExpiredException) {
            throw new CardExpiredException("Invalid card Expired.");
        } catch (CreditCardCvvException creditCardCvvException){
            throw new CreditCardCvvException("Invalid card cvv.");
        }
		
		return true;
	}

    /**
	 * Unbinds a credit card for the user.
	 * @param userCredentials The user credentials who wants to unbind a credit card.
	 * @return True if unbinding is successful.
     * @throws com.escooter.api.exceptions.UserCredentialsException
	 */
	public boolean unbindCreditCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		creditCardService.unbindCreditCard(userCredentials.getAccount());
		return true;
	}

	/**
	 * Bind a member card to the user.
	 * @param userCredentials The user who wants to bind a member card.
	 * @param memberCard The member card to bind.
	 * @return True if binding is successful.
     * @throws com.escooter.api.exceptions.UserCredentialsException
	 */
	public boolean bindMemberCard(UserCredentials userCredentials, MemberCard memberCard) throws UserCredentialsException, CardExpiredException {

		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		try {
            memberCardService.bindMemberCard(userCredentials.getAccount(),memberCard);
        } catch (CardExpiredException cardExpiredException) {
            throw new CardExpiredException("Invalid card Expired.");
        }

		return true;
	}


	/**
	 * Unbinds a member card for the user.
	 * @param user The user who wants to unbind a member card.
	 * @return True if unbinding is successful.
	 */
	public boolean unbindMemberCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		memberCardService.unbindMemberCard(userCredentials.getAccount());
		return true;
	}

	

	/**
	 * get the credit card from the user.
	 * @param userCredentials The user who wants to get their credit card.
	 * @return The user's credit card.
     * @throws com.escooter.api.exceptions.UserCredentialsException
	 */
	public CreditCard getCreditCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		return creditCardService.getCreditCard(userCredentials.getAccount());
	}

	/**
	 * get the member card from the user.
	 * @param userCredentials The user who wants to get their member card.
	 * @return The user's member card.
	 */
	public MemberCard getMemberCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		return memberCardService.getMemberCard(userCredentials.getAccount());
	}
}
