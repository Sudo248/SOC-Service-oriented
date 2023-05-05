package com.sudo248.cartservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSupplierProductDto {
    private String  supplierId, productId;
    private int amount;
    private double totalPrice;
}
