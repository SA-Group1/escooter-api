package com.escooter.api.model;

/**
 * Represents the model type of an e-scooter.
 */
public class EscooterModelType {
    private String modelId;
    private int modelPrice;
    private int usefulLife;
    private int manufacturerId;
    private int feePerMinute;

    /**
     * Returns the model ID of the e-scooter.
     * @return The model ID.
     */
     public String getModelId(){
        return modelId;
    }
    /**
     * Returns the price of the e-scooter model.
     * @return The model price.
     */
    public int getModelPrice(){
        return modelPrice;
    }
    /**
     * Returns the useful life of the e-scooter model.
     * @return The useful life.
     */
    public int getUsefulLife(){
        return usefulLife;
    }
    /**
     * Returns the manufacturer ID of the e-scooter model.
     * @return The manufacturer ID.
     */
    public int getManufacturerId(){
        return manufacturerId;
    }
    /**
     * Returns the fee per minute for using the e-scooter model.
     * @return The fee per minute.
     */
    public int getFeePerMinute(){
        return feePerMinute;
    }
}
