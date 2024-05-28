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

/**
 * Service class for managing member card operations.
 */
@Service
public class MemberCardService {

    @Autowired
    private MemberCardRepository memberCardRepository;

    /**
     * Binds a member card to a user account.
     *
     * @param account The user account.
     * @param memberCard The member card to bind.
     * @return True if binding is successful.
     * @throws CardExpiredException If the member card is expired.
     */
    public boolean bindMemberCard(String account, MemberCard memberCard) throws CardExpiredException {

        if (!isMemberCardNotExpired(memberCard.getExpirationDate())) {
            throw new CardExpiredException("Invalid card Expired.");
        }

        memberCardRepository.addMemberCard(memberCard);
        memberCardRepository.bindMemberCard(account, memberCard.getCardNumber());

        return true;
    }

    /**
     * Unbinds a member card for the user.
     *
     * @param account The user account.
     * @return True if unbinding is successful.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public boolean unbindMemberCard(String account) throws UserCredentialsException {

        String cardNumber = memberCardRepository.unbindMemberCard(account);
        memberCardRepository.deleteMemberCard(cardNumber);

        return true;
    }

    /**
     * Retrieves the member card of a user.
     *
     * @param account The user account.
     * @return The user's member card.
     */
    public MemberCard getMemberCard(String account) {

        return memberCardRepository.getMemberCard(account);
    }

    /**
     * Checks if the member card is not expired.
     *
     * @param expirationDate The expiration date of the member card.
     * @return True if the member card is not expired.
     */
    private boolean isMemberCardNotExpired(String expirationDate) {
        YearMonth yearMonth = YearMonth.parse(expirationDate, DateTimeFormatter.ofPattern("MMyy"));
        return !LocalDate.now().isAfter(yearMonth.atDay(1));
    }
}
