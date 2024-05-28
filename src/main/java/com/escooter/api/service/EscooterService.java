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
     *
     * @param escooterId The ID of the e-scooter.
     * @param modelId The modle ID of the e-scooter.
     * @return is add escooter sucessful
     */
    public boolean addEscooter(String escooterId, String modelId) {
        return escooterRepository.addEscooter(escooterId, modelId);
    }

    /**
     * Retrieves the GPS coordinates of an e-scooter by its ID.
     *
     * @param escooterId The ID of the e-scooter.
     * @return The GPS coordinates of the e-scooter.
     */
    public GPS getEscooterGpsById(String escooterId) {
        return escooterRepository.getEscooterGpsById(escooterId);
    }

    /**
     * Retrieves an e-scooter by its ID.
     *
     * @param scooterId The ID of the e-scooter.
     * @return The e-scooter if found, null otherwise.
     */
    public Escooter getEscooter(String scooterId) {
        Escooter escooter = escooterRepository.queryEscooterById(scooterId);
        return escooter;
    }

    /**
     * Updates the GPS location of an e-scooter.
     *
     * @param escooterId The ID of the e-scooter.
     * @param gps The new GPS location of the e-scooter.
     * @return true if updating the GPS location is successful.
     */
    public boolean updateGps(String escooterId, GPS gps) {
        escooterRepository.updateGps(escooterId, gps);
        return true;
    }

    /**
     * Checks the status of an e-scooter.
     *
     * @param escooterId The ID of the e-scooter.
     * @return The status of the e-scooter.
     */
    public String getEscooterStatus(String escooterId) {
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        return escooter.getStatus();
    }

    /**
     * Queries the rented e-scooter by account.
     *
     * @param account The user account.
     * @return The rented e-scooter.
     */
    public Escooter queryRentedEscooterByAccount(String account) {
        return escooterRepository.queryRentedEscooterByAccount(account);
    }

    /**
     * Updates the parking status of an e-scooter.
     *
     * @param escooterId The ID of the e-scooter.
     * @param status The new parking status.
     * @return true if updating the parking status is successful.
     */
    public boolean updateEscooterParkStatusbyId(String escooterId, String status) {
        return escooterRepository.updateEscooterParkStatusbyId(escooterId, status);
    }

    /**
     * Shows available e-scooters within a certain radius from the given GPS
     * location.
     *
     * @param gps The GPS location to search around.
     * @return A list of available e-scooters within the specified radius.
     */
    public List<Escooter> showAvailableEscooter(GPS gps) {
        int i = 0;
        List<Escooter> escooters;
        do {
            escooters = escooterRepository.queryAvailableEscooters(gps, 0.5 * ++i);
        } while (escooters.isEmpty() && i < 2);
        return escooters;
    }

    /**
     * Updates the battery level of the specified e-scooter.
     *
     * @param escooterId The ID of the e-scooter to update.
     * @param batteryLevel The new battery level.
     * @return true if the battery level was successfully updated.
     */
    public boolean updateBatteryLevel(String escooterId, int batteryLevel) {
        return escooterRepository.updateBatteryLevel(escooterId, batteryLevel);
    }

    /**
     * Retrieves all e-scooter IDs.
     *
     * @return A list of all e-scooter IDs.
     */
    public List<String> getEscooterId() {
        return escooterRepository.queryEscooters();
    }
}
