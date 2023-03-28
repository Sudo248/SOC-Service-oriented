package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.CategoryProductDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.entity.CategoryProduct;
import com.sudo248.discoveryservice.repository.entity.Supplier;

import java.util.List;

public interface CategoryProductService {
    List<ProductDto> getProductByIdCategory(String id);
    CategoryProductDto toDto(CategoryProduct categoryProduct);
    CategoryProduct toEntity(CategoryProductDto categoryProductDto);

}
