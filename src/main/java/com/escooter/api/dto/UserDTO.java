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
     * @param account
     * @param userName
     * @param password
     * @param email
    */

    public UserDTO() {}

    public UserDTO(String account, String userName, String password, String email) {
        this.account = account;
        this.userName = userName;
        this.password = password;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }

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
