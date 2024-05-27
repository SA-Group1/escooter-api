package com.escooter.api.model;

/**
 * Represents user credentials.
 */
public class UserCredentials {

    private String account;
    private String password;

    /**
     * Constructs a new UserCredentials with the specified account and password.
     *
     * @param account The user's account.
     * @param password The user's password.
     */
    public UserCredentials(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
