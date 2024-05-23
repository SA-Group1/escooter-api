package com.escooter.api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
import com.escooter.api.repository.EscooterRepository;

/**
 * Service class for managing e-scooters.
 */
@Service
public class EscooterService {
    @Autowired
    EscooterRepository escooterRepository;
    
    /**
     * Adds a new e-scooter to the repository.
     * @param escooterId The ID of the e-scooter.
     * @param modelName The model name of the e-scooter.
     * @param status The status of the e-scooter.
     */
    public boolean addEscooter(String modelId) {
        return escooterRepository.addEscooter(modelId);
    }

    /**
     * Updates the GPS location of an e-scooter.
     * @param escooterId The ID of the e-scooter.
     * @param gps The new GPS location of the e-scooter.
     */
    public boolean updateGps(String escooterId, GPS gps) {
        escooterRepository.updateGps(escooterId, gps);
        return true;
    }

    /**
    * Checks if the specified e-scooter is currently rented.
    *
    * @param escooterId The ID of the e-scooter to check
    * @return get escooter status
    */
    public String getEscooterStatus(String escooterId){
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        return escooter.getStatus();
    }

    /**
    * Updates the battery level of the specified e-scooter.
    *
    * @param escooterId   The ID of the e-scooter to update
    * @param batteryLevel The new battery level
    * @return true if the battery level was successfully updated
    */
    public boolean updateBatteryLevel(String escooterId, int batteryLevel){
        return escooterRepository.updateBatteryLevel(escooterId,batteryLevel);
    }

    /**
    * get all Escooter id.
    */
    public List<String> getEscooterId(){
        return escooterRepository.queryEscooters();
    }
}
