package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductCartDto {
    private SupplierDto supplier;
    private ProductDto product;
    private double distance;
    private int amountLeft;
    private double price, soldAmount, rate;
}
