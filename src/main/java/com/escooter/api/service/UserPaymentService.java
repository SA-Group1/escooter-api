package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.CreditCardCvvException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.CreditCard;
import com.escooter.api.model.MemberCard;
import com.escooter.api.model.UserCredentials;

/**
 * Service class for managing user payment operations, including binding and unbinding credit cards and member cards.
 */
@Service
public class UserPaymentService {

    @Autowired
    private CreditCardService creditCardService;
	@Autowired
    private MemberCardService memberCardService;
	@Autowired
	private UserCredentialService userCredentialService;

    /**
     * Binds a credit card to the user.
     *
     * @param userCredentials The user credentials.
     * @param creditCard      The credit card to bind.
     * @param cvv             The CVV (Card Verification Value) of the credit card.
     * @return True if binding is successful.
     * @throws UserCredentialsException If the user credentials are invalid.
     * @throws CardExpiredException     If the credit card is expired.
     * @throws CreditCardCvvException   If the credit card CVV is invalid.
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
     *
     * @param userCredentials The user credentials.
     * @return True if unbinding is successful.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
	public boolean unbindCreditCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		creditCardService.unbindCreditCard(userCredentials.getAccount());
		return true;
	}

	/**
     * Binds a member card to the user.
     *
     * @param userCredentials The user credentials.
     * @param memberCard      The member card to bind.
     * @return True if binding is successful.
     * @throws UserCredentialsException If the user credentials are invalid.
     * @throws CardExpiredException     If the member card is expired.
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
     *
     * @param userCredentials The user credentials.
     * @return True if unbinding is successful.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
	public boolean unbindMemberCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		memberCardService.unbindMemberCard(userCredentials.getAccount());
		return true;
	}

	/**
     * Retrieves the credit card of the user.
     *
     * @param userCredentials The user credentials.
     * @return The user's credit card.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
	public CreditCard getCreditCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		return creditCardService.getCreditCard(userCredentials.getAccount());
	}

	/**
     * Retrieves the member card of the user.
     *
     * @param userCredentials The user credentials.
     * @return The user's member card.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
	public MemberCard getMemberCard(UserCredentials userCredentials) throws UserCredentialsException {
		if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

		return memberCardService.getMemberCard(userCredentials.getAccount());
	}
}
