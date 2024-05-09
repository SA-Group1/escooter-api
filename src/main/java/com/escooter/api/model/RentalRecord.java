package com.escooter.api.model;
import java.time.LocalTime;
public class RentalRecord {
    private String userAccount;
    private String escooterId;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isPaid;
}
