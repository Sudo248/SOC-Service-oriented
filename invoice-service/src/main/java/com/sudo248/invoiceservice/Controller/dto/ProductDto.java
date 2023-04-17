package com.sudo248.invoiceservice.Controller.dto;

public class ProductDto {
    private String name,description,sku;

    public ProductDto(String name, String description, String sku) {
        this.name = name;
        this.description = description;
        this.sku = sku;
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
