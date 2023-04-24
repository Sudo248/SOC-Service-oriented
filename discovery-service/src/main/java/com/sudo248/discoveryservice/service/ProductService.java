package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.entity.Product;
import com.sudo248.discoveryservice.repository.entity.Supplier;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByName(String name);

    ProductDto getProductById(String id);
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);

}
