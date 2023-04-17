package com.sudo248.invoiceservice.Controller.dto;

public class SupplierProductDto {
    private ProductDto productDto;
    private SupplierDto supplierDto;
    private int amountLeft;
    private double price;
    private double soldAmount;
    private double rate;

    public SupplierProductDto(ProductDto productDto, SupplierDto supplierDto, int amountLeft, double price, double soldAmount, double rate) {
        this.productDto = productDto;
        this.supplierDto = supplierDto;
        this.amountLeft = amountLeft;
        this.price = price;
        this.soldAmount = soldAmount;
        this.rate = rate;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public SupplierDto getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(SupplierDto supplierDto) {
        this.supplierDto = supplierDto;
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
