package com.escooter.api.model;

import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class Escooter {
    private int escooterId;
    private String modelId;
    private String status;
    private int betteryLevel;
    private int feePerMinutes;
    private List<MaintenanceRecord> maintenanceRecords;

    public Escooter(int escooterId, String modelId, String status){
        this.escooterId = escooterId;
        this.modelId = modelId;
        this.status = status;
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
    
}


