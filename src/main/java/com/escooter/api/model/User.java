package com.escooter.api.model;
import java.util.List;
public class User {
    private String account;
    private String userName;
    private String password;
    private Escooter rentingEscooter;
    private CreditCardModel creditCard;
    private MemberCard memberCard;
    private List<RentalRecord> rentalRecords;
    
    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getuserName() {
        return userName;
    }
}
