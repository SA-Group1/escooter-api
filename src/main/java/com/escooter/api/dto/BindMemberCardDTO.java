package com.escooter.api.dto;

import com.escooter.api.model.MemberCard;
import com.escooter.api.model.UserCredentials;

/**
 * DTO (Data Transfer Object) for binding member card to a user.
 */
public class BindMemberCardDTO {

    private final UserCredentialsDTO userCredentialsDTO;
    private final MemberCardDTO memberCardDTO;

    /**
     * Constructs a new BindMemberCardDTO with the specified details.
     *
     * @param account        User account for credentials.
     * @param password       User password for credentials.
     * @param cardNumber     Member card number.
     * @param expirationDate Member card expiration date.
     */
    public BindMemberCardDTO(String account , String password, String cardNumber, String expirationDate){
        this.userCredentialsDTO = new UserCredentialsDTO(account , password);
        this.memberCardDTO = new MemberCardDTO(cardNumber, expirationDate);
    }

    public UserCredentials getUserCredentials(){
        UserCredentials userCredentials= new UserCredentials(userCredentialsDTO.getAccount(), userCredentialsDTO.getPassword());
        return userCredentials;
    }

    public MemberCard getMemberCard(){
        MemberCard memberCard = new MemberCard();
        memberCard.setCardNumber(memberCardDTO.getCardNumber());
        memberCard.setExpirationDate(memberCardDTO.getExpirationDate());
        return memberCard;
    }
}
