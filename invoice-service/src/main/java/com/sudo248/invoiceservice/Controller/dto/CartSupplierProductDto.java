package com.sudo248.invoiceservice.Controller.dto;

import java.io.Serializable;

public class CartSupplierProductDto implements Serializable {
    private SupplierProductDto supplierProductDto;
    private int amount;
    private Double totalPrice;
    private String cartId;
    public CartSupplierProductDto(SupplierProductDto supplierProductDto, int amount, Double totalPrice) {
        this.supplierProductDto = supplierProductDto;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public CartSupplierProductDto(SupplierProductDto supplierProductDto, int amount, Double totalPrice, String cartId) {
        this.supplierProductDto = supplierProductDto;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.cartId = cartId;
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

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public CartSupplierProductDto() {
    }

}
