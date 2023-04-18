package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByName(String name);

    ProductDto getProductById(String productId);
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);

}
