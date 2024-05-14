package com.escooter.api.model;

import java.util.List;
public class Escooter {
    private int escooterId;
    private String modelId;
    private String status;
    private int betteryLevel;
    private int feePerMinutes;
    private List<MaintenanceRecord> maintenanceRecords;
    private GPS gps;

    public void setEscooterId(int escooterId){
        this.escooterId = escooterId;
    }

    public void setModelId(String modelId){
        this.modelId = modelId;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setBetteryLevel(int betteryLevel){
        this.betteryLevel = betteryLevel;
    }

    public void setFeePerMinutes(int feePerMinutes){
        feePerMinutes = 10;     // if
        this.feePerMinutes = feePerMinutes;
    }

    public void setGPS(double longitude, double latitude) {
        this.gps = new GPS(longitude, latitude);
    }

    public int getEscooterId(){
        return escooterId;
    }
    public String getStatus(){
        return status;
    }
    public String getModelName(){
        return modelId;
    }
    public int getBetteryLevel(){
        return betteryLevel;
    }
    public int getFeePerMinutes(){
        return feePerMinutes;
    }
    public List<MaintenanceRecord> getMaintenanceRecords(){
        return maintenanceRecords;
    }
    
    public GPS getGPS() {
        return gps;
    }
}


