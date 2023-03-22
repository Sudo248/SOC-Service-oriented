package com.sudo248.discoveryservice.controller.dto;

import java.util.List;

public class ProductDto {
    private int productId;
    private String name,description,sku;
    private List<ImageDto> images;
    private List<SupplierProductDto> supplierProducts;
    private List<CategoryProductDto> categoryProducts;
    public ProductDto() {
    }

    public ProductDto(int productId, String name, String description, String sku) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.sku = sku;
    }



    public ProductDto(int productId, String name, String description, String sku, List<ImageDto> images) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.images = images;
    }

    public List<SupplierProductDto> getSupplierProducts() {
        return supplierProducts;
    }

    public void setSupplierProducts(List<SupplierProductDto> supplierProducts) {
        this.supplierProducts = supplierProducts;
    }

    public List<CategoryProductDto> getCategoryProducts() {
        return categoryProducts;
    }

    public void setCategoryProducts(List<CategoryProductDto> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
