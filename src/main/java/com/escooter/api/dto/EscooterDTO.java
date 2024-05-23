package com.escooter.api.dto;

/**
 * Represents an electric scooter.
 */
public class EscooterDTO {
    private String escooterId;
    private String modelId;
    private String status;
    private int batteryLevel;

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

    public String getEscooterId(){
        return escooterId;
    }

    public String getModelId(){
        return modelId;
    }

    public String getStatus(){
        return status;
    }

    public int getBatteryLevel(){
        return batteryLevel;
    }
}


