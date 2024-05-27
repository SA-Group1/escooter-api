package com.escooter.api.dto;

import com.escooter.api.model.GPS;

/**
 * DTO(Data Transfer Object) for updating GPS information. This class holds the
 * data for an e-scooter and its GPS details.
 */
public class UpdateGpsDTO {

    private final String escooterId;
    private final GpsDTO gpsDTO;

    /**
     * Constructs a new UpdateGPSDTO with the specified details.
     *
     * @param escooterId The escooter id.
     * @param longitude The GPS longitude data.
     * @param latitude The GPS latitude data.
     */
    public UpdateGpsDTO(String escooterId, double longitude, double latitude) {
        this.escooterId = escooterId;
        this.gpsDTO = new GpsDTO(longitude, latitude);
    }

    public String getEscooterId() {
        return escooterId;
    }

    public GPS getGps() {
        return new GPS(gpsDTO.getLongitude(), gpsDTO.getLatitude());
    }
}
