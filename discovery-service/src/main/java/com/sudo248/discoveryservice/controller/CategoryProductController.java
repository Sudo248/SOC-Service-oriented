package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.CategoryProductService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/discovery")
@RestController
public class CategoryProductController {
    @Autowired
    private CategoryProductService categoryProductService;
    @GetMapping("/categories/{id}/products")
    public ResponseEntity<BaseResponse<?>> getProductByIdCategory(@PathVariable int id){
        List<ProductDto> productDtos = categoryProductService.getProductByIdCategory(id);
        return ResponseEntity.ok(new BaseResponse<>(200,true,"OK", productDtos));
    }
}
