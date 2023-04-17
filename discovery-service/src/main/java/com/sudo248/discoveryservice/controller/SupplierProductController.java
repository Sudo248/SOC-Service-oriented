package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequestMapping("/api/v1/discovery")
@RestController
public class SupplierProductController {
    private SupplierProductService supplierProductService;

    public SupplierProductController(SupplierProductService supplierProductService) {
        this.supplierProductService = supplierProductService;
    }

    @GetMapping("/supplierProduct")
    public ResponseEntity<BaseResponse<?>> getAllSupplierProducts() {
        List<SupplierProductDto> supplierProducts = supplierProductService.getAllSupplierProducts();
        return BaseResponse.ok(supplierProducts);
    }
    @GetMapping("/suppliers/{name}/products")
    public ResponseEntity<BaseResponse<?>> getProductBySupplierName(@PathVariable String name){
        List<ProductDto> productDtos = supplierProductService.getProductBySupplierName(name);
        return BaseResponse.ok(productDtos);
    }

    @GetMapping("/suppliers/{name}/products/{id}")
    public ResponseEntity<BaseResponse<?>> getProductInfoBySupplierNameProductId(@PathVariable("name") String name, @PathVariable("id") String id ){
        SupplierProductDto productDto = supplierProductService.getProductInfoBySupplierNameProductId(name, id);
        return BaseResponse.ok(productDto);
    }
}
