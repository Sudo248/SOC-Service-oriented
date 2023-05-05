package com.sudo248.cartservice.controller;

import com.sudo248.cartservice.controller.dto.AddSupplierProductDto;
import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.service.CartSupplierProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartSupplierProductController {
    private final CartSupplierProductService cartSupplierProductService;

    public CartSupplierProductController(CartSupplierProductService cartSupplierProductService) {
        this.cartSupplierProductService = cartSupplierProductService;
    }

    @PostMapping("/item")
    public ResponseEntity<BaseResponse<?>> addSupplierProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody AddSupplierProductDto addSupplierProductDto
    ) {
        CartDto savedCart = cartSupplierProductService.addSupplierProductToCart(userId, addSupplierProductDto);
        return BaseResponse.ok(savedCart);
    }

    @DeleteMapping("/{cartId}/item")
    public ResponseEntity<BaseResponse<?>> deleteSupplierProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("cartId") String cartId,
            @RequestParam("supplierId") String supplierId,
            @RequestParam("productId") String productId
    ) {
        return Utils.handleException(() -> {
            CartDto savedCart = cartSupplierProductService.deleteSupplierProduct(userId, cartId, supplierId, productId);
            return BaseResponse.ok(savedCart);
        });
    }

    @PutMapping("/{cartId}/item")
    public ResponseEntity<BaseResponse<?>> updateSupplierProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("cartId") String cartId,
            @RequestBody List<AddSupplierProductDto> addSupplierProductDtoList
    ) {
        CartDto savedCart = cartSupplierProductService.updateSupplierProductToCart(userId, cartId, addSupplierProductDtoList);
        return BaseResponse.ok(savedCart);
    }
}
