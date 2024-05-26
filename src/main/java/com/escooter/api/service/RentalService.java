package com.escooter.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.RentalRepository;
/**
 * Service class for managing rental service.
 */
@Service
public class RentalService {

    @Autowired
    EscooterService escooterService;
    @Autowired
    UserCredentialService userCredentialService;
    @Autowired
    UserService userService;
    @Autowired
    RentalRepository rentalRepository;
    
    /**
     * Shows available e-scooters within a certain radius from the given GPS location.
     * @param gps The GPS location to search around.
     * @return A list of available e-scooters within the specified radius.
     */
    public List<Escooter> showAvailableEscooter(GPS gps){
        return escooterService.showAvailableEscooter(gps);
    }
    
    /**
     * Rents an e-scooter to a user.
     * @param user The user renting the e-scooter.
     * @param escooter The e-scooter to be rented.
     * @return A rental record of the transaction.
     */
    public Escooter rentEscooter(UserCredentials userCredentials, String escooterId) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        Escooter rentEscooter = escooterService.rentEscooter(userCredentials.getAccount(), escooterId);
        
        return rentEscooter;
    }

    /**
    * Updates the parking status of the e-scooter associated with the given account.
    *
    * @param account  The account identifier of the user
    * @param password The password of the user (not used in this method, consider security implications)
    * @return true if the e-scooter's parking status was successfully updated, false otherwise
    */
    public boolean updateEscooterParkStatus(UserCredentials userCredentials) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        Escooter escooter = escooterService.queryRentedEscooterByAccount(userCredentials.getAccount());
        if (escooter == null) {
            return false;
        }

        String status = escooter.getStatus();
        status = status.equals("Rented") ? "Rented_parking" : "Rented";
        escooterService.updateEscooterParkStatusbyId(escooter.getEscooterId(), status);
        return true;
    }


    /**
     * Returns a rented e-scooter.
     * @param user The user returning the e-scooter.
     * @param escooter The e-scooter to be returned.
     * @return 
     */
    public boolean returnEscooter(UserCredentials userCredentials)throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        int userId = userService.queryUserIdByAccount(userCredentials.getAccount());
        Escooter escooter = escooterService.queryRentedEscooterByAccount(userCredentials.getAccount());
        if (escooter == null) {
            return false;
        }

        GPS gps = escooter.getGPS();
        boolean res = rentalRepository.checkWithinReturnArea(gps.getLongitude(), gps.getLatitude());
        if (!res) {
            return false;
        }

        String escooterId = escooter.getEscooterId();
        String modelId = escooter.getModelId();

        return escooterService.returnEscooter(userId, escooterId, modelId);
    }
}