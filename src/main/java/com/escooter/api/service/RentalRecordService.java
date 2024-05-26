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
     * Retrieves a list of rental records for a user based on account credentials.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @return A list of rental records associated with the user.
     */
    public List<RentalRecord> getRentalRecordList(UserCredentials userCredentials) throws UserCredentialsException {

        if(!userCredentialService.verifyUserCredentials(userCredentials)){
			throw new UserCredentialsException("Invalid credentials.");
		}

        return rentalRecordRepository.queryRentalRecordsByUserAccount(userCredentials.getAccount());
    }

    public boolean createRentalRecord(int userId, String escooterId){

        rentalRecordRepository.createRentalRecord(userId, escooterId);
        return true;
    }
}
