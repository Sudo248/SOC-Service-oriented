package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductInfoDto;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

    public SupplierProductController(SupplierProductService supplierProductService) {
        this.supplierProductService = supplierProductService;
    }

    @GetMapping("/supplierProduct")
    public ResponseEntity<BaseResponse<?>> getAllSupplierProducts(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        return Utils.handleException(() -> {
            List<SupplierProductDto> supplierProducts = supplierProductService.getAllSupplierProducts(userId);
            return BaseResponse.ok(supplierProducts);
        });

    }

    @GetMapping("/suppliers/{supplierName}/products")
    public ResponseEntity<BaseResponse<?>> getProductBySupplierName(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable String supplierName
    ) {
        return Utils.handleException(() -> {
            List<ProductDto> productDtos = supplierProductService.getProductBySupplierName(userId, supplierName);
            return BaseResponse.ok(productDtos);
        });

    }

    @GetMapping("/suppliers/{supplierName}/products/{productId}")
    public ResponseEntity<BaseResponse<?>> getProductInfoBySupplierNameProductId(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("supplierName") String supplierName,
            @PathVariable("productId") String productId
    ) {
        return Utils.handleException(() -> {
            SupplierProductDto productDto = supplierProductService.getProductInfoBySupplierNameProductId(userId, supplierName, productId);
            if (productDto == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist product");
            }
            return BaseResponse.ok(productDto);
        });
    }

    @GetMapping("/suppliers/products")
    public ResponseEntity<BaseResponse<?>> getAllProductInfo(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        return Utils.handleException(() -> {
            List<SupplierProductInfoDto> supplierProductInfoDtos = supplierProductService.getAllSupplierProductInfo(userId);
            if (supplierProductInfoDtos == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist product");
            }
            return BaseResponse.ok(supplierProductInfoDtos);
        });
    }

    @PatchMapping("/order/{productId}/{supplierId}/{amount}")
    public ResponseEntity<BaseResponse<?>> orderProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("productId") String productId,
            @PathVariable("supplierId") String supplierId,
            @PathVariable("amount") int amount
    ) {
        return Utils.handleException(() -> {
            SupplierProductDto supplierProductDto = supplierProductService.orderProduct(userId, productId, supplierId, amount);
            return BaseResponse.ok(supplierProductDto);
        });
    }
}
