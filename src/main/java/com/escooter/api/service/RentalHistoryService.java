package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.escooter.api.model.*;
import com.escooter.api.repository.RentalRecordRepository;
@Service
public class RentalHistoryService {
    @Autowired
    private RentalRecordRepository recordRepository;

    private List<RentalRecord> rentalRecords;
    
    public void getRentRecordList(){
        
    }
}
