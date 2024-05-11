package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.escooter.api.model.*;
@Service
public class RentalService {
    @Autowired
    private List<Escooter> escooters;
    private String memberName;
    /*public Escooter getEscooter(String scooterId){
        return new Escooter();
    }*/
    /*public List<Escooter> showAvailableEscooter(GPS gps){
        return 我不知道怎麼寫
    }*/
    public RentalRecord rentEscooter(User user, Escooter escooter){
        return new RentalRecord();
    }
    public RentalRecord returnEscooter(User user, Escooter escooter){
        return new RentalRecord();
    }
}
