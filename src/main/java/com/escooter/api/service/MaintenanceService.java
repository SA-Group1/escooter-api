package com.escooter.api.service;

import org.springframework.stereotype.Service;

import com.escooter.api.model.Escooter;
import com.escooter.api.model.MaintenanceRecord;
/**
 * Service for handling maintenance-related operations for e-scooters.
 */
@Service
public class MaintenanceService {
    /**
     * Reports an issue with the specified e-scooter.
     *
     * @param message  A description of the issue
     * @param escooter The e-scooter that has the issue
     * @return true indicating the report was successful
     */
    public boolean report(String message, Escooter escooter){
        return true;
    }
    /**
     * Requests a charging operation for the specified e-scooter.
     *
     * @param escooter The e-scooter that needs charging
     * @return true indicating the charging request was successful
     */
    public boolean chargingRequest(Escooter escooter){
        return true;
    }
    /**
     * Marks the maintenance as finished for the specified e-scooter.
     *
     * @param escooter           The e-scooter that underwent maintenance
     * @param maintenanceRecord  The maintenance record detailing the work done
     */
    public void finishMaintence(Escooter escooter, MaintenanceRecord maintenanceRecord){
        
    }
}
