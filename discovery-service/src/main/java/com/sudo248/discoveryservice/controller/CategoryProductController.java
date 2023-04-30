package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.service.CategoryProductService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequestMapping("/api/v1/discovery")
@RestController
public class CategoryProductController {
    private CategoryProductService categoryProductService;

    public CategoryProductController(CategoryProductService categoryProductService) {
        this.categoryProductService = categoryProductService;
    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<BaseResponse<?>> getProductByIdCategory(
            @PathVariable String categoryId
    ){
        List<ProductDto> productDtos = categoryProductService.getProductByIdCategory(categoryId);
        return BaseResponse.ok(productDtos);
    }
}
