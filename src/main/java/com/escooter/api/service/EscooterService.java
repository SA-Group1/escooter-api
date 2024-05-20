package com.escooter.api.service;

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
    * @return true if the e-scooter is not available (i.e., it is rented), false otherwise
    */
    public boolean isRent(String escooterId){
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if(!escooter.getStatus().equals("Available")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
    * Checks if the specified e-scooter has been returned.
    *
    * @param escooterId The ID of the e-scooter to check
    * @return false if the e-scooter is still rented or rented and parked, true otherwise
    */
    public boolean isReturn(String escooterId){
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if(escooter.getStatus().equals("Rented")){
            return false;
        }
        else if(escooter.getStatus().equals("Rented_parling")){
            return false;
        }
        else{
            return true;
        }
    }

    /**
    * Checks if the specified e-scooter is currently parked while rented.
    *
    * @param escooterId The ID of the e-scooter to check
    * @return true if the e-scooter is rented and parked, false otherwise
    */
    public boolean getParkingStatus(String escooterId){
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if(escooter.getStatus().equals("Rented_parking")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
    * Updates the battery level of the specified e-scooter.
    *
    * @param escooterId   The ID of the e-scooter to update
    * @param batteryLevel The new battery level
    * @return true if the battery level was successfully updated
    */
    public boolean updateBetteryLevel(String escooterId, int batteryLevel){
        return escooterRepository.updateBetteryLevel(escooterId,batteryLevel);
    }
}
