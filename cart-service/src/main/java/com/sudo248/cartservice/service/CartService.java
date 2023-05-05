package com.sudo248.cartservice.service;

import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.controller.dto.CartSupplierProductDto;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartDto creNewCart(String userId);
    CartDto updateStatusCart(String userId);
    CartDto getCartById(String userId, String cartId, boolean hasRoute);

    CartDto getActiveCartByUserId(String userId);

    Integer getCountItemActiveCart(String userId);

    List<CartSupplierProductDto> getSupplierProduct(String userId, String cartId, List<CartSupplierProduct> list, boolean hasRoute);
}
