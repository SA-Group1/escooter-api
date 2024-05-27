package com.escooter.api.dto;

import com.escooter.api.model.UserCredentials;

/**
 * DTO (Data Transfer Object) for uploading user photo.
 */
public class UploadUserPhotoDTO {

    private final UserCredentialsDTO userCredentialsDTO;
    private byte[] image;

    /**
     * Constructs a new UploadUserPhotoDTO with the specified details.
     *
     * @param account The user account.
     * @param password The user password.
     * @param image The user's photo as a byte array.
     */
    public UploadUserPhotoDTO(String account, String password, byte[] image) {
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
        return new UserCredentials(userCredentialsDTO.getAccount(), userCredentialsDTO.getPassword());
    }
}
