package com.escooter.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.escooter.api.model.*;
import com.escooter.api.repository.EscooterRepository;
import com.escooter.api.repository.RentalRecordRepository;
import com.escooter.api.repository.UserRepository;
/**
 * Service class for managing rental service.
 */
@Service
public class RentalService {
    @Autowired
	private EscooterRepository escooterRepository;
    @Autowired
    private RentalRecordRepository rentalRecordRepository;
    @Autowired
    private UserRepository userRepository;
    
    private List<Escooter> escooters;
    
    /**
     * Retrieves an e-scooter by its ID.
     * @param scooterId The ID of the e-scooter.
     * @return The e-scooter if found, null otherwise.
     */
    public Escooter getEscooter(String scooterId){
        Escooter escooter = escooterRepository.queryEscooterById(scooterId);
        return escooter;
    }
    
    /**
     * Shows available e-scooters within a certain radius from the given GPS location.
     * @param gps The GPS location to search around.
     * @return A list of available e-scooters within the specified radius.
     */
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
    
    /**
     * Rents an e-scooter to a user.
     * @param user The user renting the e-scooter.
     * @param escooter The e-scooter to be rented.
     * @return A rental record of the transaction.
     */
    public Escooter rentEscooter(User user, String escooterId) {
        Escooter escooter = escooterRepository.queryEscooterById(escooterId);
        if (!escooter.getStatus().equals("Available")) {
            return null;
        } else {
            user = userRepository.queryUserByAccount(user.getAccount());
            rentalRecordRepository.createRentalRecord(user.getUserId(), escooter.getEscooterId());
            escooterRepository.updateStatus(escooter.getEscooterId(), "Rented");
            escooter.setStatus("Rented");
            return escooter;
        }
    }


    /**
     * Returns a rented e-scooter.
     * @param user The user returning the e-scooter.
     * @param escooter The e-scooter to be returned.
     * @return A rental record of the transaction.
     */
    public RentalRecord returnEscooter(User user, Escooter escooter){
        return new RentalRecord();
    }

    /**
     * Checks if a point is within a specified radius of another point.
     * @param x1 The longitude of the first point.
     * @param y1 The latitude of the first point.
     * @param x2 The longitude of the second point.
     * @param y2 The latitude of the second point.
     * @param radius The radius to check within, in meters.
     * @return True if the second point is within the radius of the first point, false otherwise.
     */
    static boolean isWithinRadius(double x1, double y1, double x2, double y2, double radius) {
        x1 = LatitudeAndLongitude2Meter(x1);
        y1 = LatitudeAndLongitude2Meter(y1);
        x2 = LatitudeAndLongitude2Meter(x2);
        y2 = LatitudeAndLongitude2Meter(y2);
        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return distance <= radius;
    }
    /**
     * Converts latitude and longitude to meters.
     * @param num The value to convert.
     * @return The converted value in meters.
     */
    static double LatitudeAndLongitude2Meter(double num) {
        return (double)num / 0.00000900900901;
    }
    /**
     * Converts meters to latitude and longitude.
     * @param num The value to convert.
     * @return The converted value in latitude and longitude.
     */
    static double Meter2LatitudeAndLongitude(double num) {
        return (double)num * 0.00000900900901;
    }

    public boolean updateEscooterParkStatus(String account, String passowrd) {
        Escooter escooter = escooterRepository.queryRentedEscooterByAccount(account);
        if (escooter == null) {
            return false;
        }

        String status = escooter.getStatus();
        status = status.equals("Rented") ? "Rented_parking" : "Rented";
        escooterRepository.updateEscooterParkStatusbyId(escooter.getEscooterId(), status);
        return true;
    }
}