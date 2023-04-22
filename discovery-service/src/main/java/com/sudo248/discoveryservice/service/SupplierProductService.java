package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;

import java.util.List;

public interface SupplierProductService {
    List<SupplierProductDto> getAllSupplierProducts(String userId);
    List<ProductDto> getProductBySupplierName(String userId, String supplierName);
    SupplierProductDto getProductInfoBySupplierNameProductId(String userId, String supplierName, String productId);

    SupplierProductDto orderProduct(String userId, String productId, String supplierId, int amount);

    SupplierProductDto toDto(String userId, SupplierProduct supplierProduct);
    SupplierProduct toEntity(SupplierProductDto supplierProductDto);
    List<SupplierProductDto> getSupplierProductsByProductId(String userId, String productId);
}