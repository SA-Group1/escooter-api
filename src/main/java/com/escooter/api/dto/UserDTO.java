package com.escooter.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.escooter.api.model.CreditCard;
import com.escooter.api.model.Escooter;
import com.escooter.api.model.MemberCard;
import com.escooter.api.model.RentalRecord;

/**
 * DTO (Data Transfer Object) for representing User information.
 */
public class UserDTO {
    private String account;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime registrationTime;
    private CreditCard creditCard;
    private MemberCard memberCard;

    /**
     * Default constructor for UserDTO.
     */
    public UserDTO() {}

    /**
     * Constructs a UserDTO with the specified account.
     * @param account The user account.
     */
    public UserDTO(String account){
        this.account = account;
    }

    /**
     * Constructs a UserDTO with the specified account, username, password, and email.
     * @param account The user account.
     * @param userName The user username.
     * @param password The user password.
     * @param email The user email.
     */
    public UserDTO(String account, String userName, String password, String email) {
        this.account = account;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    /**
     * Sets the user account.
     * @param account The user account.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Sets the user username.
     * @param userName The user username.
     */
    public void setUserName(String username) {
        this.userName = username;
    }

    /**
     * Sets the user password.
     * @param password The user password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user email.
     * @param email The user email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the registration time.
     * @param registrationTime The registration time.
     */
    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
    
    /**
     * return the user account.
     * @return The user account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * return the user user name.
     * @return The user account.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * return the user password.
     * @return The user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * return the user email.
     * @return The user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * return the user phone number.
     * @return The user phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
