package com.escooter.api.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCardModel {
    private int creditcard_id;
    private int expiration_data;

    public int getCreditCard_ID(){
        return creditcard_id;
    }
    public int getExpiration_Data(){
        return expiration_data;
    }
    public void setCreditCard_ID(int creditcard_id){
        this.creditcard_id = creditcard_id;
    }
}
