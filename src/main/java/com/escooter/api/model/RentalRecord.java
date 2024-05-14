package com.escooter.api.model;
import java.time.LocalTime;
public class RentalRecord {
    private String userAccount;
    private String escooterId;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isPaid;

    
    public String getUserAccount(){
        return userAccount;
    }
    public String getEscooterId(){
        return escooterId;
    }
    public LocalTime getStartTime(){
        return startTime;
    }
    public LocalTime getEndTime(){
        return endTime;
    }
    public boolean getIsPaid(){
        return isPaid;
    }

    public void setUserAccount(String userAccount){
        this.userAccount = userAccount;
    }

    public void setEscooterId(String escooterId){
        this.escooterId = escooterId;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }

    public void setIsPaid(boolean isPaid)
    {
        this.isPaid = isPaid;
    }

    
}
