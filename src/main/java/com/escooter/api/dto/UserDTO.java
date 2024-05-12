package com.escooter.api.dto;

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

    public String getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
