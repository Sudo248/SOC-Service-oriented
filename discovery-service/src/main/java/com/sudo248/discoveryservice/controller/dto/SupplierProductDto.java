package com.sudo248.discoveryservice.controller.dto;

public class SupplierProductDto {
    private int supplierId;
    private int productId;
    private double distance;
    private int amountLeft;
    private double price, soldAmount, rate;
    public SupplierProductDto() {
    }

    public SupplierProductDto(int supplierId, int productId, int amountLeft, double price, double soldAmount, double rate) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.amountLeft = amountLeft;
        this.price = price;
        this.soldAmount = soldAmount;
        this.rate = rate;
    }

    public SupplierProductDto(int supplierId, int productId, double distance, int amountLeft, double price, double soldAmount, double rate) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.distance = distance;
        this.amountLeft = amountLeft;
        this.price = price;
        this.soldAmount = soldAmount;
        this.rate = rate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(int amountLeft) {
        this.amountLeft = amountLeft;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(double soldAmount) {
        this.soldAmount = soldAmount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public SupplierProductDto(int supplierId, int productId) {
        this.supplierId = supplierId;
        this.productId = productId;
    }
}
