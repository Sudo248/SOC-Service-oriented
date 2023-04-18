package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductDto {
    private String supplierId;
    private String productId;
    private double distance = 0.0;
    private int amountLeft = 0;
    private double price = 0.0, soldAmount = 0.0, rate = 0.0;
}
