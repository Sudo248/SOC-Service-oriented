package com.sudo248.invoiceservice.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductDto implements Serializable {
    private SupplierDto supplier;
    private ProductDto product;
    private double distance;
    private int amountLeft;
    private double price, soldAmount, rate;
}
