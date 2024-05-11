package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escooter.api.repository.*;

@Service
public class EscooterService {
    @Autowired
    EscooterRepository escooterRepository;
    public void addEscooter(int escooterId,String modelName,String status){
        escooterRepository.addEscooter(escooterId, modelName, status);
    }

}
