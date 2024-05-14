package com.escooter.api.dto;

/**
 * DTO (Data Transfer Object) for representing GPS information.
 */
public class GPSDTO {
    private double longitude;
    private double latitude;

    /**
     * Constructs a new GPSDTO with the GPS details.
     * @param longitude
     * @param latitude
    */
    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }
}
