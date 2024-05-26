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
    @Autowired
    UserService userService;
    @Autowired
    RentalRecordService rentalRecordService;
    
    /**
     * Adds a new e-scooter to the repository.
     * @param modelId The modle ID of the e-scooter.
     * @return is add escooter sucessful
     */
    public boolean addEscooter(String modelId) {
        return escooterRepository.addEscooter(modelId);
    }

    /**
     * Retrieves an e-scooter by its ID.
     * @param scooterId The ID of the e-scooter.
     * @return The e-scooter if found, null otherwise.
     */
    public Escooter getEscooter(String scooterId){
        Escooter escooter = escooterRepository.queryEscooterById(scooterId);
        return escooter;
    }

    public boolean returnEscooter(int userId, String escooterId, String modelId){
        return escooterRepository.returnEscooter(userId, escooterId, modelId);
    }

    /**
     * Updates the GPS location of an e-scooter.
     * @param escooterId The ID of the e-scooter.
     * @param gps The new GPS location of the e-scooter.
     * @return is update gps sucessful
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

    public Escooter queryRentedEscooterByAccount(String account){
        return escooterRepository.queryRentedEscooterByAccount(account);
    }

    public boolean updateEscooterParkStatusbyId(String escooterId , String status){
        return escooterRepository.updateEscooterParkStatusbyId(escooterId, status);
    }

    /**
     * Rents an e-scooter to a user.
     * @param user The user renting the e-scooter.
     * @param escooter The e-scooter to be rented.
     * @return A rental record of the transaction.
     */
    public Escooter rentEscooter(String account, String escooterId){

        Escooter rentEscooter = escooterRepository.queryEscooterById(escooterId);
        if (!rentEscooter.getStatus().equals("Available")) {
            return null;
        }

        Escooter rentedEscooter = escooterRepository.queryRentedEscooterByAccount(account);
        if (rentedEscooter != null) {
            return null;
        }

        int userId = userService.queryUserIdByAccount(account);
        rentalRecordService.createRentalRecord(userId,escooterId);
        escooterRepository.updateStatus(escooterId, "Rented");
        rentEscooter.setStatus("Rented");
        
        return rentEscooter;
    }

    /**
     * Shows available e-scooters within a certain radius from the given GPS location.
     * @param gps The GPS location to search around.
     * @return A list of available e-scooters within the specified radius.
     */
    public List<Escooter> showAvailableEscooter(GPS gps){
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
    * @param escooterId   The ID of the e-scooter to update
    * @param batteryLevel The new battery level
    * @return true if the battery level was successfully updated
    */
    public boolean updateBatteryLevel(String escooterId, int batteryLevel){
        return escooterRepository.updateBatteryLevel(escooterId,batteryLevel);
    }

    /**
    * get all Escooter id.
    * @return return escooter id list.
    */
    public List<String> getEscooterId(){
        return escooterRepository.queryEscooters();
    }
}
