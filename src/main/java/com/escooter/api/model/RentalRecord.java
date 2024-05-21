package com.escooter.api.model;

/**
 * Represents a rental record for an e-scooter.
 */
public class RentalRecord {
    private int userId;
    private String escooterId;
    private String startTime;
    private String endTime;
    private boolean isPaid;
    private String modelId;
    private double feePerMinutes;
    private int duration;
    private int totalFee;

    /**
     * Returns the user account associated with this rental.
     * @return The user account.
     */
    public int getUserId(){
        return userId;
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

    public String getModelId(){
        return modelId;
    }

    public double getFeePerMinutes() {
        return feePerMinutes;
    }
    
    public int getDuration() {
        return duration;
    }

    public int getTotalFee() {
        return totalFee;
    }

    /**
     * Sets the user account associated with this rental.
     * @param userAccount The user account.
     */
    public void setUserId(int userId){
        this.userId = userId;
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

    public void setModelId(String modelId){
        this.modelId = modelId;
    }

    public void setFeePerMinutes(double feePerMinutes) {
        this.feePerMinutes = feePerMinutes;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }
}
