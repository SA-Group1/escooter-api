package com.escooter.api.dto;

/**
 * DTO (Data Transfer Object) for representing User information.
 */
public class UserDTO {
    private String account;
    private String userName;
    private String password;

    /**
     * @param account
     * @param userName
     * @param password
    */

    public UserDTO(String account, String userName, String password) {
        this.account = account;
        this.userName = userName;
        this.password = password;
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
}
