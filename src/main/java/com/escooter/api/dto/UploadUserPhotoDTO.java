package com.escooter.api.dto;

public class UploadUserPhotoDTO {
    private String account;
    private String password;
    private byte[] image;

    public UploadUserPhotoDTO(){};

    public UploadUserPhotoDTO(String account, String password, byte[] image){
        this.account = account;
        this.password = password;
        this.image = image;
    }

    /**
     * Sets the user account.
     * @param account The user account.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Sets the user password.
     * @param password The user password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user image.
     * @param image The user image in BLOB format.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Returns the user image.
     * @return The user image in BLOB format.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * return the user account.
     * @return The user account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * return the user password.
     * @return The user password.
     */
    public String getPassword() {
        return password;
    }
}   

