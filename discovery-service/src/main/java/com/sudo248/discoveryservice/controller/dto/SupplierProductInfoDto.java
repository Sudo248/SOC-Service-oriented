package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductInfoDto {
    private String supplierId;
    private String productId;
    private String categoryId;
    private List<ImageDto> productImages;
    private String description;
    private String productName;
    private String categoryName;
    private int amountLeft = 0;
    private double price = 0.0;
    private int soldAmount = 0;
    private double rate = 0.0;
    private String sku;
}
