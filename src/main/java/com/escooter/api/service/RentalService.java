package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.escooter.api.model.*;
import com.escooter.api.repository.EscooterRepository;
import com.escooter.api.repository.RentalRecordRepository;
import com.escooter.api.repository.UserRepository;
/**
 * Service class for managing rental service.
 */
@Service
public class RentalService {
    @Autowired
	private EscooterRepository escooterRepository;
    @Autowired
    private RentalRecordRepository rentalRecordRepository;
    @Autowired
    private UserRepository userRepository;
    
    private List<Escooter> escooters;
    
    /**
     * Retrieves an e-scooter by its ID.
     * @param scooterId The ID of the e-scooter.
     * @return The e-scooter if found, null otherwise.
     */
    public Escooter getEscooter(String scooterId){
        Escooter escooter = escooterRepository.queryEscooterById(scooterId);
        return escooter;
    }
    
    /**
     * Shows available e-scooters within a certain radius from the given GPS location.
     * @param gps The GPS location to search around.
     * @return A list of available e-scooters within the specified radius.
     */
    public List<Escooter> showAvailableEscooter(GPS gps){
        int i = 0;
        do {
            escooters = escooterRepository.queryAvailableEscooters(gps.getLongitude(), gps.getLatitude(), 0.5 * ++i);
            System.out.println("i=" + i);
        } while (escooters.isEmpty() && i < 2);
        return escooters;
    }
    
    /**
     * Rents an e-scooter to a user.
     * @param user The user renting the e-scooter.
     * @param escooter The e-scooter to be rented.
     * @return A rental record of the transaction.
     */
    public Escooter rentEscooter(User user, String escooterId) {
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if (!escooter.getStatus().equals("Available")) {
            return null;
        } else {
            user = userRepository.queryUserByAccount(user.getAccount());
            rentalRecordRepository.createRentalRecord(user.getUserId(), escooter.getEscooterId());
            escooterRepository.updateStatus(escooter.getEscooterId(), "Rented");
            escooter.setStatus("Rented");
            return escooter;
        }
    }


    /**
     * Returns a rented e-scooter.
     * @param user The user returning the e-scooter.
     * @param escooter The e-scooter to be returned.
     * @return 
     */
    public boolean returnEscooter(String account, String password) {
        User user = userRepository.queryUserByAccount(account);
        int userId = user.getUserId();
        Escooter escooter = escooterRepository.queryRentedEscooterByAccount(account);
        String escooterId = escooter.getEscooterId();
        String modelId = escooter.getModelId();
        return escooterRepository.returnEscooter(userId, escooterId, modelId);
    }

    public boolean updateEscooterParkStatus(String account, String passowrd) {
        Escooter escooter = escooterRepository.queryRentedEscooterByAccount(account);
        if (escooter == null) {
            return false;
        }

        String status = escooter.getStatus();
        status = status.equals("Rented") ? "Rented_parking" : "Rented";
        escooterRepository.updateEscooterParkStatusbyId(escooter.getEscooterId(), status);
        return true;
    }
}