package com.escooter.api.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCardModel {
    private String creditcard_id;
    private String expiration_data;

    public String getCreditCard_ID(){
        return creditcard_id;
    }
    public String getExpiration_Data(){
        return expiration_data;
    }
    public void setCreditCard_ID(String creditcard_id, String expiration_data){
        this.creditcard_id = creditcard_id;
        this.expiration_data = expiration_data;
    }
}
