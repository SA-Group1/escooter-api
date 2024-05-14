package com.escooter.api.model;
import java.time.LocalDateTime;
public class RentalRecord {
    private String userAccount;
    private String escooterId;
    private String startTime;
    private String endTime;
    private boolean isPaid;

    
    public String getUserAccount(){
        return userAccount;
    }
    public String getEscooterId(){
        return escooterId;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
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

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public void setIsPaid(boolean isPaid)
    {
        this.isPaid = isPaid;
    }

    
}
