package com.escooter.api.model;

import java.util.List;

/**
 * Represents an electric scooter.
 */
public class Escooter {
    private String escooterId;
    private String modelId;
    private String status;
    private int batteryLevel;
    private double feePerMinutes;
    private List<MaintenanceRecord> maintenanceRecords;
    private GPS gps;

    public void setEscooterId(String escooterId){
        this.escooterId = escooterId;
    }

    public void setModelId(String modelId){
        this.modelId = modelId;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel = batteryLevel;
    }

    
    public void setFeePerMinutes(double feePerMinutes){
        this.feePerMinutes = feePerMinutes;
    }

    public void setGPS(double longitude, double latitude) {
        this.gps = new GPS(longitude, latitude);
    }

    public String getEscooterId(){
        return escooterId;
    }
    
    public String getStatus(){
        return status;
    }

    public String getModelId(){
        return modelId;
    }

    public int getBatteryLevel(){
        return batteryLevel;
    }

    public double getFeePerMinutes(){
        return feePerMinutes;
    }

    public List<MaintenanceRecord> getMaintenanceRecords(){
        return maintenanceRecords;
    }
    
    public GPS getGPS() {
        return gps;
    }
}


