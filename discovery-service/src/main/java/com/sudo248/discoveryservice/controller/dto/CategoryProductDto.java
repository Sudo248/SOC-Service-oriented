package com.sudo248.discoveryservice.controller.dto;

public class CategoryProductDto {
    private String categoryId;
    private String productId;

    public CategoryProductDto() {
    }

    public CategoryProductDto(String categoryId, String productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
