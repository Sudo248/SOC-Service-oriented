package com.sudo248.invoiceservice.Controller.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private String cartId;
    private Double totalPrice;
    private int totalAmount;
    private String userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartSupplierProductDto> getCartSupplierProductDtoList() {
        return cartSupplierProductDtoList;
    }

    public void setCartSupplierProductDtoList(List<CartSupplierProductDto> cartSupplierProductDtoList) {
        this.cartSupplierProductDtoList = cartSupplierProductDtoList;
    }

    public CartDto() {
    }

    public CartDto(String cartId) {
        this.cartId = cartId;
        totalAmount = 9;
        totalPrice = 50000.0;
        cartSupplierProductDtoList = new ArrayList<>();
        addDefaultValue();
    }
    private void addDefaultValue(){
        SupplierDto supplierDto1 = new SupplierDto("Vinamilk", "Url1");
        SupplierDto supplierDto2 = new SupplierDto("TH True Milk", "Url2");
        SupplierDto supplierDto3 = new SupplierDto("Co gai Ha Lan", "Url3");
        ProductDto productDto1 = new ProductDto("Sua tuoi 180ml","Sua tuoi","ST001");
        ProductDto productDto2 = new ProductDto("Sua tuoi 350ml","Sua tuoi","ST002");
        SupplierProductDto supplierProductDto1 = new SupplierProductDto(productDto1,supplierDto1,100, 5000,300, 1.2);
        SupplierProductDto supplierProductDto2 = new SupplierProductDto(productDto1,supplierDto2,200, 6000,300, 1.2);
        cartSupplierProductDtoList.add(new CartSupplierProductDto(supplierProductDto1, 4, 20000.0));
        cartSupplierProductDtoList.add(new CartSupplierProductDto(supplierProductDto2, 5, 30000.0));

    }
}
