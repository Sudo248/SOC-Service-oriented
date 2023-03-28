package com.sudo248.discoveryservice.controller.dto;

import com.sudo248.discoveryservice.repository.entity.Image;

public class ImageDto {
    private String imageId;
    private String  url, ownerId;

    public ImageDto() {
    }

    public ImageDto(String imageId, String url, String ownerId) {
        this.imageId = imageId;
        this.url = url;
        this.ownerId = ownerId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
