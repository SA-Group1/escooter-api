package com.escooter.api.model;

public class GPS {
    private double longitude;
    private double latitude;

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
