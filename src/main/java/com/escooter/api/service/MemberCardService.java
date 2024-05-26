package com.escooter.api.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.CardExpiredException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.MemberCard;
import com.escooter.api.repository.MemberCardRepository;

@Service
public class MemberCardService {
    @Autowired
    private MemberCardRepository memberCardRepository;

    public boolean bindMemberCard(String account, MemberCard memberCard) throws CardExpiredException {

        if(!isMemberCardNotExpired(memberCard.getExpirationDate())) {
            throw new CardExpiredException("Invalid card Expired.");
        }

        memberCardRepository.addMemberCard(memberCard);
        memberCardRepository.bindMemberCard(account,memberCard.getCardNumber());

		return true;
	}

    /**
	 * Unbinds a credit card for the user.
	 * @param account The user account.
	 * @return True if unbinding is successful.
     * @throws com.escooter.api.exceptions.UserCredentialsException
	 */
	public boolean unbindMemberCard(String account) throws UserCredentialsException {

		String cardNumber = memberCardRepository.unbindMemberCard(account);
        memberCardRepository.deleteMemberCard(cardNumber);

		return true;
	}

    /**
	 * get the member card from the user.
	 * @param userCredentials The user who wants to get their member card.
	 * @return The user's member card.
	 */
	public MemberCard getMemberCard(String account){

		return memberCardRepository.getMemberCard(account);
	}

    private  boolean isMemberCardNotExpired(String expirationDate){
        YearMonth yearMonth = YearMonth.parse(expirationDate,DateTimeFormatter.ofPattern("MMyy"));
        return !LocalDate.now().isAfter(yearMonth.atDay(1));
    }
}
