package com.escooter.api.model;

/*
* Represents a User.
 */
public class User {

    private int userId;
    private String account;
    private String userName;
    private String email;
    private String registrationTime;
    private String phoneNumber;
    private CreditCard creditCard;
    private MemberCard memberCard;
    private byte[] image;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public MemberCard getmemberCard() {
        return memberCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImage() {
        return image;
    }
}
