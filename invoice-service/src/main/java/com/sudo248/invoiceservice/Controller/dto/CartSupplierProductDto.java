package com.sudo248.invoiceservice.Controller.dto;

import java.util.List;

public class CartSupplierProductDto {
    private SupplierProductDto supplierProductDto;
    private int amount;
    private Double totalPrice;

    public CartSupplierProductDto(SupplierProductDto supplierProductDto, int amount, Double totalPrice) {
        this.supplierProductDto = supplierProductDto;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public SupplierProductDto getSupplierProductDto() {
        return supplierProductDto;
    }

    public void setSupplierProductDto(SupplierProductDto supplierProductDto) {
        this.supplierProductDto = supplierProductDto;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
