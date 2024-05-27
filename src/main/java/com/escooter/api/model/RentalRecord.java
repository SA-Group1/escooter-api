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

    public int getUserId() {
        return userId;
    }

    public String getEscooterId() {
        return escooterId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public String getModelId() {
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEscooterId(String escooterId) {
        this.escooterId = escooterId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setModelId(String modelId) {
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
