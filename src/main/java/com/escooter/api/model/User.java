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

    public void setImage(byte[] image) {
        this.image = image;
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

    /**
     * Returns the registration time.
     * @return The registration time.
     */
    public String getRegistrationTime() {
        //
        return registrationTime;
    }

    /**
    * Gets the credit card associated with this user.
    *
    * @return The credit card object
    */
    public CreditCard getCreditCard(){
        return creditCard;
    }

    /**
    * Gets the member card associated with this user.
    *
    * @return The member card object
    */
    public MemberCard getmemberCard(){
        return memberCard;
    }

    /**
    * Sets the credit card for this user.
    *
    * @param creditCard The credit card object to set
    */
    public void setCreditCard(CreditCard creditCard){
        this.creditCard = creditCard;
    }
    
    /**
    * Sets the member card for this user.
    *
    * @param memberCard The member card object to set
    */
    public void setMemberCard(MemberCard memberCard){
        this.memberCard = memberCard;
    }
    
    /**
    * Gets the member card associated with this user.
    *
    * @return The member card object
    */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
    * Sets the member card for this user.
    *
    * @param memberCard The member card object to set
    */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImage() {
        return image;
    }
}
