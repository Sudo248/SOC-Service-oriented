package com.sudo248.discoveryservice.controller.dto;

import com.sudo248.discoveryservice.repository.entity.Product;

import java.util.List;

public class CategoryDto {
    private String categoryId;
    private String  name, image;
    private List<ProductDto> products;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }


    public CategoryDto() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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



    public CategoryDto(String categoryId, String name, String image) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
    }

    public CategoryDto(String categoryId, String name, String image, List<ProductDto> products) {
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
        this.products = products;
    }
}
