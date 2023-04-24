package com.sudo248.invoiceservice.Controller.dto;

import java.io.Serializable;

public class InvoiceDto implements Serializable {
    private String invoiceId,address, status;
    private CartDto cartDto;
    private PaymentDto paymentDto;
    private PromotionDto promotionDto;
//    private UserDto userDto;
    private Double totalPrice, totalPromotionPrice, finalPrice;

    public InvoiceDto() {
    }


    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }

    public PromotionDto getPromotionDto() {
        return promotionDto;
    }

    public void setPromotionDto(PromotionDto promotionDto) {
        this.promotionDto = promotionDto;
    }

//    public UserDto getUserDto() {
//        return userDto;
//    }
//
//    public void setUserDto(UserDto userDto) {
//        this.userDto = userDto;
//    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPromotionPrice() {
        return totalPromotionPrice;
    }

    public void setTotalPromotionPrice(Double totalPromotionPrice) {
        this.totalPromotionPrice = totalPromotionPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }


}
