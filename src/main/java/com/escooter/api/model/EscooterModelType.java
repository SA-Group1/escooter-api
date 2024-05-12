package com.escooter.api.model;


public class EscooterModelType {
    private String modelId;
    private int modelPrice;
    private int usefulLife;
    private int manufacturerId;
    private int feePerMinute;

     public String getModelId(){
        return modelId;
    }
    public int getModelPrice(){
        return modelPrice;
    }
    public int getUsefulLife(){
        return usefulLife;
    }
    public int getManufacturerId(){
        return manufacturerId;
    }
    public int getFeePerMinute(){
        return feePerMinute;
    }
}
