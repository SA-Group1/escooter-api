package com.escooter.api.dto;


/**
 * DTO (Data Transfer Object) for representing User information.
 */
public class UserDTO {
    private String account;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;

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
     * Sets the user account.
     * @param account The user account.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Sets the user username.
     * @param userName The user username.
     * @param password The user password.
     */
    public void setUserName(String username, String password) {
        this.userName = username;
        this.password = password;
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
