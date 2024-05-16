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
    public boolean updateGps(int escooterId, GPS gps) {
        escooterRepository.updateGps(escooterId, gps);
        return true;
    }

    public boolean isRent(int escooterId){
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if(!escooter.getStatus().equals("Available")){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isReturn(int escooterId){
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

    public boolean getParkingStatus(int escooterId){
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if(escooter.getStatus().equals("Rented_parking")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateBetteryLevel(int escooterId,int batteryLevel){
        return escooterRepository.updateBetteryLevel(escooterId,batteryLevel);
    }
}
