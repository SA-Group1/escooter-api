package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.Escooter;
import com.escooter.api.model.MaintenanceRecord;
@Service
public class MaintenanceService {
    @Autowired
    public boolean report(String message, Escooter escooter){
        return true;
    }
    public boolean chargingRequest(Escooter escooter){
        return true;
    }
    public void finishMaintence(Escooter escooter, MaintenanceRecord maintenanceRecord){
        
    }
}
