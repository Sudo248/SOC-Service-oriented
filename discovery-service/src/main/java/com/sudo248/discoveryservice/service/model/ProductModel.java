package com.sudo248.discoveryservice.service.model;

import lombok.Data;

@Data
public class ProductModel {
    private String productId;
    private String name,description,sku;

    public ProductModel() {
    }

    public ProductModel(String productId, String name, String description, String sku) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.sku = sku;
    }
}