package com.escooter.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateGPSDTO {
    @JsonProperty("escooter")
    private EscooterDTO escooterDTO;
    @JsonProperty("gps")
    private GPSDTO gpsDTO;

    /**
     * Constructs a new UpdateGPSDTO with the specified details.
     * @param escooterDTO The escooter data.
     * @param gpsDTO The GPS data.
     */
    public UpdateGPSDTO(EscooterDTO escooterDTO, GPSDTO gpsDTO){
        this.escooterDTO = escooterDTO;
        this.gpsDTO = gpsDTO;
    }

    /**
     * Returns the escooter data.
     * @return The escooter data.
     */
    public EscooterDTO getEscooterDTO(){
        return escooterDTO;
    }

    /**
     * Returns the GPS data.
     * @return The GPS data.
     */
    public GPSDTO getGPSDTO(){
        return gpsDTO;
    }
}
