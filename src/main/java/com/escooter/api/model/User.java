package com.escooter.api.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/*
* Represents a User.
*/
public class User {
    private String account;
    private String userName;
    private String password;
    private Escooter rentingEscooter;
    private CreditCard creditCard;
    private MemberCard memberCard;
    private List<RentalRecord> rentalRecords;

    public User() {}
    
    public User(String account, String userName, String password) {
        this.account = account;
        this.userName = userName;
        this.password = password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Returns the user account.
     * @return the user account.
     */
    public String getAccount() {
        return account;
    }
    /**
     * Returns the user name.
     * @return the user name.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Returns the user password.
     * @return the user password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns the password verify.
     * @return the password verify.
     */
    public boolean verifyPassword(String password) {
        // 如何驗證
        return true;
    }

    /*public Escooter getRentingEscooter() {
        // 
        return new Escooter();
    }*/

    public boolean setRentEscooter(Escooter escooter) {
        // 
        return true;
    }

    public CreditCard getCreditCard() {
        // 
        return new CreditCard("", "" , "");
    }

    public boolean setCreditCard(CreditCard creditCard) {
        // 
        return true;
    }

    public MemberCard getMemberCard() {
        // 
        return new MemberCard("", "");
    }

    public boolean setMemberCard(MemberCard memberCard) {
        // 
        return true;
    }

    public List<RentalRecord> getRentalRecords() {
        // 
        return new ArrayList<>();
    }

    public void addRentalRecords() {
        // 
    }
}
