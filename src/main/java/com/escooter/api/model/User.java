package com.escooter.api.model;
import java.util.ArrayList;
import java.util.List;


/*
* Represents a User.
*/
public class User {
    private int userId;
    private String account;
    private String userName;
    private String password;
    private String email;
    private String registrationTime;
    private Escooter rentingEscooter;
    private CreditCard creditCard;
    private MemberCard memberCard;
    private List<RentalRecord> rentalRecords;

    /**
     * Default constructor.
     */
    public User() {}

    /**
     * Constructs a User with the specified account.
     * @param account The user account.
     */
    public User(String account) {
        this.account = account;
    }
    /**
     * Constructs a User with the specified account.
     * @param account The user account.
     * @param password THe user password.
     */
    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
    
    /**
     * Constructs a User with the specified account, userName, and password.
     * @param account The user account.
     * @param userName The user name.
     * @param password The user password.
     */
    public User(String account, String userName, String password) {
        this.account = account;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Sets the user ID.
     * @param userId The user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the user ID.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user account.
     * @param account The user account.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Sets the user name.
     * @param userName The user name.
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
    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    /**
     * Returns the user account.
     * @return The user account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * Returns the user name.
     * @return The user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the user password.
     * @return The user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user email.
     * @return The user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Verifies the user password.
     * @param password The password to verify.
     * @return True if the password is correct, false otherwise.
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
    /**
     * Returns the registration time.
     * @return The registration time.
     */
    public String getRegistrationTime() {
        //
        return registrationTime;
    }

    public List<RentalRecord> getRentalRecords() {
        // 
        return new ArrayList<>();
    }

    public void addRentalRecords() {
        // 
    }
}
