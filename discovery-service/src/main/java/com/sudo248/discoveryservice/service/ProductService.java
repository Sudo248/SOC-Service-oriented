package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(String userId, ProductDto productDto);

    ProductDto putProduct(String userId, ProductDto productDto);

    List<ProductDto> getAllProducts(String userId);
    List<ProductDto> getProductsByName(String userId, String name);

    ProductDto getProductById(String userId, String productId);
    ProductDto toDto(String userId, Product product);
    Product toEntity(ProductDto productDto);

}
