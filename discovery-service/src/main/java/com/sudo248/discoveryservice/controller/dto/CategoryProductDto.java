package com.sudo248.discoveryservice.controller.dto;

public class CategoryProductDto {
    private int categoryId;
    private int productId;

    public CategoryProductDto() {
    }

    public CategoryProductDto(int categoryId, int productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
