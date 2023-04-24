package com.sudo248.discoveryservice.service.model;

import lombok.Data;

@Data
public class CategoryModel {
    private String categoryId;
    private String name,image,supplierId;

    public CategoryModel() {
    }

    public CategoryModel(String categoryId, String name, String image, String supplierId) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
        this.supplierId = supplierId;
    }
}
