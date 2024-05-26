package com.escooter.api.dto;

/**
 * DTO (Data Transfer Object) for representing GPS coordinates.
 */
public class GpsDTO {

    private final double longitude;
    private final double latitude;

    /**
     * Constructs a new GpsDTO with the GPS details.
     *
     * @param longitude the GPS longitude
     * @param latitude  the GPS latitude
     */
    public GpsDTO(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }
    
}
