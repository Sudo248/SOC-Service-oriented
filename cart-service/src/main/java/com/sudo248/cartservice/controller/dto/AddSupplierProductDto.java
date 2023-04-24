package com.sudo248.cartservice.controller.dto;

public class AddSupplierProductDto {
    private String  supplierId, productId;
    private int amount;
    private double totalPrice;

    public AddSupplierProductDto(String supplierId, String productId, int amount, double totalPrice) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
