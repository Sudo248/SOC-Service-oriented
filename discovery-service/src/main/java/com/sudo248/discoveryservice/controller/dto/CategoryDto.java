package com.sudo248.discoveryservice.controller.dto;

import com.sudo248.discoveryservice.repository.entity.Product;

import java.util.List;

public class CategoryDto {
    private int categoryId;
    private String  name, image, supplierId;
    private List<ProductDto> products;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }


    public CategoryDto() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public CategoryDto(int categoryId, String name, String image, String supplierId) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
        this.supplierId = supplierId;
    }

    public CategoryDto(int categoryId, String name, String image, String supplierId, List<ProductDto> products) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
        this.supplierId = supplierId;
        this.products = products;
    }
}
