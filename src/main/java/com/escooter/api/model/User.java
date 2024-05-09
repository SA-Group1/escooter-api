package com.escooter.api.model;
import java.util.List;
public class User {
    private String account;
    private String userName;
    private String password;
    private Escooter rentingEscooter;
    private CreditCard creditCard;
    private MemberCard memberCard;
    private List<RentalRecord> rentalRecords;
}
