package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.ProductService;

import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discovery")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<BaseResponse<?>> addproduct(@RequestBody ProductDto productDto) {
        ProductDto savedproduct = productService.addProduct(productDto);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", savedproduct));
    }

    @GetMapping("/product")
    public ResponseEntity<BaseResponse<?>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", products));
    }
    @GetMapping("/product/{id}")
    @ResponseBody
    public ResponseEntity<BaseResponse<?>> getProductById(@PathVariable int id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", product));
    }
    @GetMapping("/product/name/{name}")
    public ResponseEntity<BaseResponse<?>> getProductsByName(@PathVariable String name) {
        List<ProductDto> products = productService.getProductsByName(name);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", products));
    }


}