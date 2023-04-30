package com.sudo248.cartservice.controller.dto;

import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private String cartId;
    private Double totalPrice;
    private int totalAmount;
    private String status;
    private List<CartSupplierProductDto> cartSupplierProducts;
}
