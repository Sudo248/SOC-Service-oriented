package com.sudo248.cartservice.service;

import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.controller.dto.CartSupplierProductDto;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartDto creNewCart(String userId);
    CartDto updateStatusCart(String cartId);
    CartDto getCartById(String cartId);

    CartDto getActiveCartByUserId(String userId);

    List<CartSupplierProductDto> getSupplierProduct(String cartId, List<CartSupplierProduct> list);
}
