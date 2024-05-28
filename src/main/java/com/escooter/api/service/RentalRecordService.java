package com.escooter.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.exceptions.UserCredentialsException;
import com.escooter.api.model.RentalRecord;
import com.escooter.api.model.UserCredentials;
import com.escooter.api.repository.RentalRecordRepository;

/**
 * Service class for managing rental history.
 */
@Service
public class RentalRecordService {

    @Autowired
    private RentalRecordRepository rentalRecordRepository;
    @Autowired
    private UserCredentialService userCredentialService;

    /**
     * Retrieves a list of rental records for a user based on account
     * credentials.
     *
     * @param userCredentials The user's credentials containing account and
     * password.
     * @return A list of rental records associated with the user.
     * @throws UserCredentialsException If the user credentials are invalid.
     */
    public List<RentalRecord> getRentalRecordList(UserCredentials userCredentials) throws UserCredentialsException {

        if (!userCredentialService.verifyUserCredentials(userCredentials)) {
            throw new UserCredentialsException("Invalid credentials.");
        }

        return rentalRecordRepository.queryRentalRecordsByUserAccount(userCredentials.getAccount());
    }

    /**
     * Creates a rental record for the specified user and e-scooter.
     *
     * @param userId The ID of the user renting the e-scooter.
     * @param escooterId The ID of the e-scooter being rented.
     * @return True if the rental record is successfully created.
     */
    public boolean createRentalRecord(int userId, String escooterId) {

        rentalRecordRepository.createRentalRecord(userId, escooterId);
        return true;
    }
}
