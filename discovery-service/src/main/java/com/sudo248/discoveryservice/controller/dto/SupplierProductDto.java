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
    private RouteDto route;
    private int amountLeft = 0;
    private double price = 0.0;
    private int soldAmount = 0;
    private double rate = 0.0;
}
