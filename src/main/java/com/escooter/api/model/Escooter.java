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
    private int feePerMinutes;
    private List<MaintenanceRecord> maintenanceRecords;
    private GPS gps;

    /**
     * Sets the ID of the electric scooter.
     * @param escooterId The electric scooter ID.
     */
    public void setEscooterId(String escooterId){
        this.escooterId = escooterId;
    }

    /**
     * Sets the model ID of the electric scooter.
     * @param modelId The model ID.
     */
    public void setModelId(String modelId){
        this.modelId = modelId;
    }

    /**
     * Sets the status of the electric scooter.
     * @param status The status.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Sets the battery level of the electric scooter.
     * @param batteryLevel The battery level.
     */
    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel = batteryLevel;
    }

    /**
     * Sets the fee per minute for using the electric scooter.
     * @param feePerMinute The fee per minute.
     */
    public void setFeePerMinutes(int feePerMinutes){
        feePerMinutes = 10;     // if
        this.feePerMinutes = feePerMinutes;
    }

    /**
     * Sets the GPS coordinates of the electric scooter.
     * @param longitude The longitude.
     * @param latitude The latitude.
     */
    public void setGPS(double longitude, double latitude) {
        this.gps = new GPS(longitude, latitude);
    }

    /**
     * Returns the electric scooter ID.
     * @return The electric scooter ID.
     */
    public String getEscooterId(){
        return escooterId;
    }
    
    /**
     * Returns the status of the electric scooter.
     * @return The status.
     */
    public String getStatus(){
        return status;
    }

    /**
     * Returns the model ID of the electric scooter.
     * @return The model ID.
     */
    public String getModelId(){
        return modelId;
    }

    /**
     * Returns the battery level of the electric scooter.
     * @return The battery level.
     */
    public int getBatteryLevel(){
        return batteryLevel;
    }

    /**
     * Returns the fee per minute for using the electric scooter.
     * @return The fee per minute.
     */
    public int getFeePerMinutes(){
        return feePerMinutes;
    }

    /**
     * Returns the maintenance records of the electric scooter.
     * @return The maintenance records.
     */
    public List<MaintenanceRecord> getMaintenanceRecords(){
        return maintenanceRecords;
    }
    
    /**
     * Returns the GPS coordinates of the electric scooter.
     * @return The GPS coordinates.
     */
    public GPS getGPS() {
        return gps;
    }
}


