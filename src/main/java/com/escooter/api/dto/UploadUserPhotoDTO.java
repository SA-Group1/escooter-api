package com.escooter.api.dto;

import com.escooter.api.model.UserCredentials;

public class UploadUserPhotoDTO {
    private UserCredentialsDTO userCredentialsDTO;
    private byte[] image;


    public UploadUserPhotoDTO(String account, String password, byte[] image){
        this.userCredentialsDTO = new UserCredentialsDTO(account, password);
        this.image = image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public UserCredentials getUserCredentials() {
        return new UserCredentials(userCredentialsDTO.getAccount(),userCredentialsDTO.getPassword());
    }
}   

