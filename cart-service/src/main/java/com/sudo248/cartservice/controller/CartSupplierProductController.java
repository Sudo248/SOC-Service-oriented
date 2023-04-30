package com.sudo248.cartservice.controller;

import com.sudo248.cartservice.controller.dto.AddSupplierProductDto;
import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.controller.dto.CartSupplierProductDto;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import com.sudo248.cartservice.service.CartSupplierProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CartSupplierProductController {
    private final CartSupplierProductService cartSupplierProductService;

    public CartSupplierProductController(CartSupplierProductService cartSupplierProductService) {
        this.cartSupplierProductService = cartSupplierProductService;
    }
    @PostMapping("/addSupplierProduct")
    public ResponseEntity<BaseResponse<?>> addSupplierProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody AddSupplierProductDto addSupplierProductDto
    ) {
        CartDto savedCart = cartSupplierProductService.addSupplierProductToCart(userId, addSupplierProductDto);
        return BaseResponse.ok(savedCart);
    }
    @PutMapping("/updateSupplierProduct")
    public ResponseEntity<BaseResponse<?>> updateSupplierProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody List<AddSupplierProductDto> addSupplierProductDtoList
    ) {
        CartDto savedCart = cartSupplierProductService.updateSupplierProductToCart(userId, addSupplierProductDtoList);
        return BaseResponse.ok(savedCart);
    }
}
