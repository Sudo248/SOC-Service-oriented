package com.sudo248.cartservice.controller.dto;

import java.io.Serializable;

public class SupplierProductDto implements Serializable {
    private SupplierDto supplierDto;
    private ProductDto productDto;
    private double distance;
    private int amountLeft;
    private double price, soldAmount, rate;

    public SupplierProductDto() {
    }

    public SupplierProductDto(SupplierDto supplierDto, ProductDto productDto, double distance, int amountLeft, double price, double soldAmount, double rate) {
        this.supplierDto = supplierDto;
        this.productDto = productDto;
        this.distance = distance;
        this.amountLeft = amountLeft;
        this.price = price;
        this.soldAmount = soldAmount;
        this.rate = rate;
    }

    public SupplierDto getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(SupplierDto supplierDto) {
        this.supplierDto = supplierDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
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
}
