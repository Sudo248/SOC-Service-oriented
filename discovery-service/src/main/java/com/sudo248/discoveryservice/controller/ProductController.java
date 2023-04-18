package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> addProduct(@RequestBody ProductDto productDto) {
        return Utils.handleException(() -> {
            ProductDto savedProduct = productService.addProduct(productDto);
            return BaseResponse.ok(savedProduct);
        });
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllProducts() {
        return Utils.handleException(() -> {
            List<ProductDto> products = productService.getAllProducts();
            return BaseResponse.ok(products);
        });
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getProductById(@PathVariable("productId") String productId) {
        return Utils.handleException(() -> {
            ProductDto product = productService.getProductById(productId);
            if (product == null) {
                BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist product");
            }
            return BaseResponse.ok(product);
        });
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<BaseResponse<?>> getProductsByName(@PathVariable("productName") String productName) {
        return Utils.handleException(() -> {
            List<ProductDto> products = productService.getProductsByName(productName);
            return BaseResponse.ok(products);
        });
    }
}