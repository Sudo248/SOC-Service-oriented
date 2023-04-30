package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productId;
    private String name = "",description = "",sku = "";
    private List<ImageDto> images = new ArrayList<>();
    private List<String> categoryIds = new ArrayList<>();
    private List<SupplierProductDto> supplierProducts = new ArrayList<>();

    public ProductDto(String productId, String name, String description, String sku, List<ImageDto> images) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.images = images;
    }
}
