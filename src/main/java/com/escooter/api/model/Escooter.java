package com.escooter.api.model;

import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class Escooter {
    private String escooterId;
    private String modelName;
    private String status;
    private int betteryLevel;
    private int feePerMinutes;
    private List<MaintenanceRecord> maintenanceRecords;

    public String getEscooterId(){
        return escooterId;
    }
    public String getStatus(){
        return status;
    }
    public String getModelName(){
        return modelName;
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


