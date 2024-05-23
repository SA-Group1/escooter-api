package com.escooter.api.model;



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
    private String phoneNumber;
    //private Escooter rentingEscooter;
    private CreditCard creditCard;
    private MemberCard memberCard;
    //private List<RentalRecord> rentalRecords;
    private byte[] image;
    


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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyPassword(String password) {
        // 如何驗證
        return true;
    }

    public String getRegistrationTime() {
        //
        return registrationTime;
    }

    public CreditCard getCreditCard(){
        return creditCard;
    }

    public MemberCard getmemberCard(){
        return memberCard;
    }

    public void setCreditCard(CreditCard creditCard){
        this.creditCard = creditCard;
    }
    
    public void setMemberCard(MemberCard memberCard){
        this.memberCard = memberCard;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImage() {
        return image;
    }
}
