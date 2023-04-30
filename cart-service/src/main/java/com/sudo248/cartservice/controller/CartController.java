package com.sudo248.cartservice.controller;

import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.service.CartService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/new/{userId}")
    public ResponseEntity<BaseResponse<?>> addCart(@PathVariable String userId) {
        CartDto savedCart = cartService.creNewCart(userId);
        return BaseResponse.ok(savedCart);
    }
    @PutMapping("/{cartId}/completed")
    public ResponseEntity<BaseResponse<?>> updateStatusCart(@PathVariable String cartId) {
        CartDto updatedCart = cartService.updateStatusCart(cartId);
        return BaseResponse.ok(updatedCart);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<BaseResponse<?>> getCartById(@PathVariable String cartId) {
        CartDto cart = cartService.getCartById(cartId);
        return BaseResponse.ok(cart);
    }
    @GetMapping("/active")
    public ResponseEntity<BaseResponse<?>> getActiveCartByUserId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        CartDto activeCart = cartService. getActiveCartByUserId(userId);
        return BaseResponse.ok(activeCart);
    }
    //Call from other service
    @GetMapping("/service/{cartId}")
    public CartDto serviceGetCartById(@PathVariable String cartId) {
        return cartService.getCartById(cartId);
    }
}
