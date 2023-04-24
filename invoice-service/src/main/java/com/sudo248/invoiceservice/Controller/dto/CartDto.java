package com.sudo248.invoiceservice.Controller.dto;

import java.io.Serializable;
import java.util.List;

public class CartDto implements Serializable {
    private String cartId;
    private Double totalPrice;
    private int totalAmount;
    private String status;
    private UserDto userDto;
    private List<CartSupplierProductDto> cartSupplierProductDtoList;
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<CartSupplierProductDto> getCartSupplierProductDtoList() {
        return cartSupplierProductDtoList;
    }

    public void setCartSupplierProductDtoList(List<CartSupplierProductDto> cartSupplierProductDtoList) {
        this.cartSupplierProductDtoList = cartSupplierProductDtoList;
    }

    public CartDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CartDto(String cartId, Double totalPrice, int totalAmount, String status, String userId, List<CartSupplierProductDto> list) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
        this.status = status;
        cartSupplierProductDtoList = list;
    }

    public CartDto(String cartId, Double totalPrice, int totalAmount, String status, String userId) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
        this.status = status;

    }

    public CartDto(String cartId, Double totalPrice, int totalAmount, String status, UserDto userDto, List<CartSupplierProductDto> cartSupplierProductDtoList) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
        this.status = status;
        this.userDto = userDto;
        this.cartSupplierProductDtoList = cartSupplierProductDtoList;
    }

}
