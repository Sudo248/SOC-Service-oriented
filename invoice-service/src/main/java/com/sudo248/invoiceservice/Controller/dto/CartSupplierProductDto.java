package com.sudo248.invoiceservice.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartSupplierProductDto {
    private SupplierProductDto supplierProduct;
    private int amount;
    private Double totalPrice;
    private String cartId;
}
