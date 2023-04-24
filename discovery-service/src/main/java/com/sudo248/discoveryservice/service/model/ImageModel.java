package com.sudo248.discoveryservice.service.model;

public class ImageModel {
    private String imageId, url,  ownerId;

    public ImageModel() {
    }

    public ImageModel(String imageId, String url, String ownerId) {
        this.imageId = imageId;
        this.url = url;
        this.ownerId = ownerId;
    }
}
