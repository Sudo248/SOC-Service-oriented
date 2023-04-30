package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.CategoryProductDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.entity.CategoryProduct;

import java.util.List;

public interface CategoryProductService {
    List<ProductDto> getProductByIdCategory(String categoryId);
    CategoryProductDto toDto(CategoryProduct categoryProduct);
    CategoryProduct toEntity(CategoryProductDto categoryProductDto);

}
