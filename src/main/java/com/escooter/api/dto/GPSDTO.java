package com.escooter.api.dto;

/**
 * DTO (Data Transfer Object) for representing GPS information.
 */
public class GPSDTO {
    private double longitude;
    private double latitude;

    /**
     * Returns the GPS Longitude.
     * @return the GPS Longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the GPS Latitude.
     * @return the GPS Latitude.
     */
    public double getLatitude() {
        return latitude;
    }
}
