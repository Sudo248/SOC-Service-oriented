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
    @PutMapping("/active/completed")
    public ResponseEntity<BaseResponse<?>> updateStatusCart(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        CartDto updatedCart = cartService.updateStatusCart(userId);
        return BaseResponse.ok(updatedCart);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<BaseResponse<?>> getCartById(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable String cartId,
            @RequestParam(value = "hasRoute", required = false, defaultValue = "false") boolean hasRoute
    ) {
        CartDto cart = cartService.getCartById(userId, cartId, hasRoute);
        return BaseResponse.ok(cart);
    }

    @GetMapping("/active")
    public ResponseEntity<BaseResponse<?>> getActiveCartByUserId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        CartDto activeCart = cartService. getActiveCartByUserId(userId);
        return BaseResponse.ok(activeCart);
    }

    @GetMapping("/active/count-item")
    public ResponseEntity<BaseResponse<?>> getCountItemActiveCartByUserId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        Integer count = cartService.getCountItemActiveCart(userId);
        return BaseResponse.ok(count);
    }

    //Call from other service
    @GetMapping("/service/{cartId}")
    public CartDto serviceGetCartById(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable String cartId,
            @RequestParam(value = "hasRoute", defaultValue = "false") boolean hasRoute
    ) {
        return cartService.getCartById(userId, cartId, hasRoute);
    }
}
