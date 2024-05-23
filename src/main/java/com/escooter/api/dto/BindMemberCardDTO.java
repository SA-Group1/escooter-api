package com.escooter.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) for binding member card to a user.
 */
public class BindMemberCardDTO {

    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("memberCard")
    private MemberCardDTO memberCardDTO;

    /**
     * Constructs a new BindMemberCardDTO with the specified details.
     * @param userDTO The user data.
     * @param memberCardDTO The member card data.
     */
    public BindMemberCardDTO(UserDTO userDTO, MemberCardDTO memberCardDTO){
        this.userDTO = userDTO;
        this.memberCardDTO = memberCardDTO;
    }

    public UserDTO getUserDTO(){
        return userDTO;
    }

    public MemberCardDTO getMemberCardDTO(){
        return memberCardDTO;
    }
}
