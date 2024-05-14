package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.escooter.api.model.*;
import com.escooter.api.repository.EscooterRepository;
@Service
public class RentalService {
    @Autowired
	private EscooterRepository escooterRepository;
    
    private List<Escooter> escooters;
    
    public Escooter getEscooter(String scooterId){
        Escooter escooter = escooterRepository.queryEscooterById(scooterId);
        return escooter;
    }
    
    public List<Escooter> showAvailableEscooter(GPS gps){
        escooters = escooterRepository.queryEscooters();
        int[] distance = new int[] {500, 1000};
        List<Escooter> escootersWithinRadius = new ArrayList<Escooter>(){};
        for (int i=0; i<distance.length && escootersWithinRadius.isEmpty(); i++) {
            for (Escooter escooter: escooters) {
                GPS escooterGPS = escooter.getGPS();
                if (isWithinRadius(gps.getLongitude(), gps.getLatitude(), escooterGPS.getLongitude(), escooterGPS.getLatitude(), Meter2LatitudeAndLongitude(distance[i]))) {
                    escootersWithinRadius.add(escooter);
                }
            }
        }
        return escootersWithinRadius;
    }
    public RentalRecord rentEscooter(User user, Escooter escooter){
        return new RentalRecord();
    }
    public RentalRecord returnEscooter(User user, Escooter escooter){
        return new RentalRecord();
    }

    static boolean isWithinRadius(double x1, double y1, double x2, double y2, double radius) {
        x1 = LatitudeAndLongitude2Meter(x1);
        y1 = LatitudeAndLongitude2Meter(y1);
        x2 = LatitudeAndLongitude2Meter(x2);
        y2 = LatitudeAndLongitude2Meter(y2);
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return distance <= radius;
    }

    static double LatitudeAndLongitude2Meter(double num) {
        return (double)num / 0.00000900900901;
    }

    static double Meter2LatitudeAndLongitude(double num) {
        return (double)num * 0.00000900900901;
    }
}
// 0.00000900900901 經度/meter
// 0.0002702703