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
            return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", supplierProducts));
        });

    }

    @GetMapping("/suppliers/{name}/products")
    public ResponseEntity<BaseResponse<?>> getProductBySupplierName(@PathVariable String name){
        return Utils.handleException(() -> {
            List<ProductDto> productDtos = supplierProductService.getProductBySupplierName(name);
            return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", productDtos));
        });

    }

    @GetMapping("/suppliers/{name}/products/{id}")
    public ResponseEntity<BaseResponse<?>> getProductInfoBySupplierNameProductId(@PathVariable("name") String name, @PathVariable("id") int id ){
        return Utils.handleException(() -> {
            SupplierProductDto productDto = supplierProductService.getProductInfoBySupplierNameProductId(name, id);
            if (productDto == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist product");
            }
            return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", productDto));
        });

    }
}
