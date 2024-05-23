package com.escooter.api.model;

/**
 * Represents a GPS.
 */
public class GPS {
    private double longitude;
    private double latitude;

    /**
     * Constructs a new GPSDTO with the GPS details.
     * @param longitude the GPS longitude
     * @param latitude  the GPS latitude
    */
    public GPS(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}
