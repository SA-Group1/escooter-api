package com.escooter.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) for binding credit card to user.
 */
public class RentEscooterDTO {

    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("escooter")
    private EscooterDTO escooterDTO;

    /**
     * Constructs a new RentEscooterDTO with the specified details.
     * @param userDTO The user data.
     * @param escooterDTO The escooter data.
     */
    public RentEscooterDTO(UserDTO userDTO, EscooterDTO escooterDTO){
        this.userDTO = userDTO;
        this.escooterDTO = escooterDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public EscooterDTO getEscooterDTO() {
        return escooterDTO;
    }
}




