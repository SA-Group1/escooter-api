package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addEscooter(int escooterId, String modelName, String status) {
        escooterRepository.addEscooter(escooterId, modelName, status);
    }

    /**
     * Updates the GPS location of an e-scooter.
     * @param escooterId The ID of the e-scooter.
     * @param gps The new GPS location of the e-scooter.
     */
    public void updateGps(int escooterId, GPS gps) {
        escooterRepository.updateGps(escooterId, gps);
    }
}
