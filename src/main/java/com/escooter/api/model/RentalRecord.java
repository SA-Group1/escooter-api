package com.escooter.api.model;
import java.time.LocalDateTime;

/**
 * Represents a rental record for an e-scooter.
 */
public class RentalRecord {
    private String userAccount;
    private String escooterId;
    private String startTime;
    private String endTime;
    private boolean isPaid;

    /**
     * Returns the user account associated with this rental.
     * @return The user account.
     */
    public String getUserAccount(){
        return userAccount;
    }

    /**
     * Returns the e-scooter ID associated with this rental.
     * @return The e-scooter ID.
     */
    public String getEscooterId(){
        return escooterId;
    }

    /**
     * Returns the start time of this rental.
     * @return The start time.
     */
    public String getStartTime(){
        return startTime;
    }

    /**
     * Returns the end time of this rental.
     * @return The end time.
     */
    public String getEndTime(){
        return endTime;
    }

    /**
     * Returns whether this rental has been paid for.
     * @return True if the rental is paid, false otherwise.
     */
    public boolean getIsPaid(){
        return isPaid;
    }

    /**
     * Sets the user account associated with this rental.
     * @param userAccount The user account.
     */
    public void setUserAccount(String userAccount){
        this.userAccount = userAccount;
    }

    /**
     * Sets the e-scooter ID associated with this rental.
     * @param escooterId The e-scooter ID.
     */
    public void setEscooterId(String escooterId){
        this.escooterId = escooterId;
    }

    /**
     * Sets the start time of this rental.
     * @param startTime The start time.
     */
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    /**
     * Sets the end time of this rental.
     * @param endTime The end time.
     */
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    /**
     * Sets whether this rental has been paid for.
     * @param isPaid True if the rental is paid, false otherwise.
     */
    public void setIsPaid(boolean isPaid)
    {
        this.isPaid = isPaid;
    }

    
}
