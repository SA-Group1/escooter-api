package com.escooter.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.EscooterOutOfServiceException;
import com.escooter.api.exceptions.RentEscooterFailException;
import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.GPS;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.RentalRepository;

/**
 * Service class for managing rental services.
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
    @Autowired
    RentalRecordService rentalRecordService;

    /**
     * Shows available e-scooters within a certain radius from the given GPS
     * location.
     *
     * @param gps The GPS location to search around.
     * @return A list of available e-scooters within the specified radius.
     */
    public List<Escooter> showAvailableEscooter(GPS gps) {
        return escooterService.showAvailableEscooter(gps);
    }

    /**
     * Rents an e-scooter to a user.
     *
     * @param userCredentials The user credentials.
     * @param escooterId The ID of the e-scooter to be rented.
     * @return The rented e-scooter if the transaction is successful, null
     * otherwise.
     * @throws UserCredentialsException If the user credentials are invalid.
     * @throws com.escooter.api.exceptions.EscooterOutOfServiceException
     * @throws com.escooter.api.exceptions.RentEscooterFailException
     */
    public Escooter rentEscooter(UserCredentials userCredentials, String escooterId) throws UserCredentialsException, EscooterOutOfServiceException, RentEscooterFailException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
            throw new UserCredentialsException("Invalid credentials.");
        }

        Escooter rentEscooter = escooterService.getEscooter(escooterId);
        if (!rentEscooter.getStatus().equals("Available")) {
            throw new EscooterOutOfServiceException("Escooter is not available.");
        }

        Escooter rentedEscooter = escooterService.queryRentedEscooterByAccount(userCredentials.getAccount());
        if (rentedEscooter != null) {
            throw new RentEscooterFailException("Rent escooter fail.");
        }

        int userId = userService.queryUserIdByAccount(userCredentials.getAccount());
        rentalRecordService.createRentalRecord(userId, escooterId);
        escooterService.updateEscooterParkStatusbyId(escooterId, "Rented");
        rentEscooter.setStatus("Rented");

        return rentEscooter;
    }

    /**
     * Updates the parking status of the e-scooter associated with the given
     * account.
     *
     * @param userCredentials The user credentials.
     * @return true if the e-scooter's parking status was successfully updated,
     * false otherwise.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public boolean updateEscooterParkStatus(UserCredentials userCredentials) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
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
     *
     * @param userCredentials The user credentials.
     * @return true if the return is successful, false otherwise.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public boolean returnEscooter(UserCredentials userCredentials) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
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

        return rentalRepository.returnEscooter(userId, escooterId, modelId);
    }
}
