package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.escooter.api.model.*;
import com.escooter.api.repository.RentalRecordRepository;
import com.escooter.api.repository.UserRepository;
@Service
public class RentalHistoryService {
    @Autowired
    private RentalRecordRepository rentalRecordRepository;
    
    @Autowired
    private UserRepository userRepository;

    private List<RentalRecord> rentalRecords;
    
    public void getRentRecordList() {
        
    }

    public List<RentalRecord> getRentalRecordList(String account, String password) {
        return rentalRecordRepository.queryRentalRecordsByUserId(account);
    }
}
