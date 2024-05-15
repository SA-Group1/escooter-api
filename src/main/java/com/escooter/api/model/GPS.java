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
    
    /**
     * Returns the GPS Longitude.
     * @return the GPS Longitude.
     */
    public double getLongitude(){
        return longitude;
    }
    /**
     * Returns the GPS Latitude.
     * @return the GPS Latitude.
     */
    public double getLatitude(){
        return latitude;
    }
    /**
     * set the GPS Longitude.
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * set the GPS Latitude.
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}
