package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.escooter.api.model.*;
import com.escooter.api.repository.RentalRecordRepository;
import com.escooter.api.repository.UserRepository;

/**
 * Service class for managing rental history.
 */
@Service
public class RentalHistoryService {
    @Autowired
    private RentalRecordRepository rentalRecordRepository;
    
    @Autowired
    private UserRepository userRepository;

    private List<RentalRecord> rentalRecords;
    
    /**
     * Placeholder method for retrieving a list of rental records.
     */
    public void getRentRecordList() {
        
    }

    /**
     * Retrieves a list of rental records for a user based on account credentials.
     * @param account The account identifier for the user.
     * @param password The password for the user's account.
     * @return A list of rental records associated with the user.
     */
    public List<RentalRecord> getRentalRecordList(String account, String password) {
        return rentalRecordRepository.queryRentalRecordsByUserAccount(account);
    }
}
