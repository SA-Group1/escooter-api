package com.escooter.api.dto;

import com.escooter.api.model.MemberCard;

/**
 * DTO (Data Transfer Object) for binding member card to a user.
 */
public class BindMemberCardDTO {

    private final String account;
    private final MemberCardDTO memberCardDTO;

    /**
     * Constructs a new BindMemberCardDTO with the specified details.
     *
     * @param account User account for credentials.
     * @param cardNumber Member card number.
     * @param expirationDate Member card expiration date.
     */
    public BindMemberCardDTO(String account, String cardNumber, String expirationDate) {
        this.account = account;
        this.memberCardDTO = new MemberCardDTO(cardNumber, expirationDate);
    }

    public String getAccount() {
        return account;
    }

    public MemberCard getMemberCard() {
        MemberCard memberCard = new MemberCard();
        memberCard.setCardNumber(memberCardDTO.getCardNumber());
        memberCard.setExpirationDate(memberCardDTO.getExpirationDate());
        return memberCard;
    }
}
