package com.escooter.api.model;
import java.util.Date;

import org.springframework.stereotype.Component;

public class MemberCard {
    private String carId;
    private String expirationDate;
    
    public MemberCard(String carId, String expirationDate) {
        this.carId = carId;
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return carId;
    }
    public String getExpirationDate() {
        return expirationDate;
    }

    public boolean isVaild() {
        // 如何驗證
        return true;
    }
}