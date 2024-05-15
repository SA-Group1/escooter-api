package com.escooter.api.dto;

/**
 * Represents an electric scooter.
 */
public class EscooterDTO {
    private int escooterId;
    private String modelId;
    private String status;

    /**
     * Sets the ID of the electric scooter.
     * @param escooterId The electric scooter ID.
     */
    public void setEscooterId(int escooterId){
        this.escooterId = escooterId;
    }

    /**
     * Sets the model ID of the electric scooter.
     * @param modelId The model ID.
     */
    public void setModelId(String modelId){
        this.modelId = modelId;
    }

    /**
     * Sets the status of the electric scooter.
     * @param status The status.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Returns the electric scooter ID.
     * @return The electric scooter ID.
     */
    public int getEscooterId(){
        return escooterId;
    }

    /**
     * Returns the model ID of the electric scooter.
     * @return The model ID.
     */
    public String getModelId(){
        return modelId;
    }

    /**
     * Returns the status of the electric scooter.
     * @return The status.
     */
    public String getStatus(){
        return status;
    }
}


