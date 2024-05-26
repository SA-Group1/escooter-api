package com.escooter.api.dto;

import com.escooter.api.model.User;

/**
 * DTO (Data Transfer Object) for representing User information.
 */
public class UserDTO {
    private String account;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private byte[] image;


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
     * Constructs a UserDTO with the specified account.
     * @param account The user account.
     * @param password The user password.
     */
    public UserDTO(String account, String password) {
        this.account = account;
        this.password = password;
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
     * Constructs a UserDTO with all fields.
     * @param account The user account.
     * @param userName The user username.
     * @param password The user password.
     * @param email The user email.
     * @param phoneNumber The user phone number.
     * @param image The user image in BLOB format.
     */
    public UserDTO(String account, String userName, String password, String email, String phoneNumber, byte[] image) {
        this.account = account;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public User getUser(){
        User user = new User();
        user.setAccount(account);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setImage(image);
        return user;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setUserName(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public String getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
