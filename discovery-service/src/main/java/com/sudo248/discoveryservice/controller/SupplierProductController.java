package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

    public SupplierProductController(SupplierProductService supplierProductService) {
        this.supplierProductService = supplierProductService;
    }

    @GetMapping("/supplierProduct")
    public ResponseEntity<BaseResponse<?>> getAllSupplierProducts() {
        return Utils.handleException(() -> {
            List<SupplierProductDto> supplierProducts = supplierProductService.getAllSupplierProducts();
            return BaseResponse.ok(supplierProducts);
        });

    }

    @GetMapping("/suppliers/{supplierName}/products")
    public ResponseEntity<BaseResponse<?>> getProductBySupplierName(@PathVariable String supplierName) {
        return Utils.handleException(() -> {
            List<ProductDto> productDtos = supplierProductService.getProductBySupplierName(supplierName);
            return BaseResponse.ok(productDtos);
        });

    }

    @GetMapping("/suppliers/{supplierName}/products/{productId}")
    public ResponseEntity<BaseResponse<?>> getProductInfoBySupplierNameProductId(
            @PathVariable("supplierName") String supplierName,
            @PathVariable("productId") String productId
    ) {
        return Utils.handleException(() -> {
            SupplierProductDto productDto = supplierProductService.getProductInfoBySupplierNameProductId(supplierName, productId);
            if (productDto == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist product");
            }
            return BaseResponse.ok(productDto);
        });

    }
}
