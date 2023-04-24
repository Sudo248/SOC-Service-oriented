package com.sudo248.cartservice.service;

import com.sudo248.cartservice.controller.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartDto creNewCart(String userId);
    CartDto updateStatusCart(String cartId);
    CartDto getCartById(String cartId);

    CartDto getActiveCartByUserId(String userId);
}
