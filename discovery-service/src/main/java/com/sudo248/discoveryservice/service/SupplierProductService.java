package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;

import java.util.List;

public interface SupplierProductService {
    List<SupplierProductDto> getAllSupplierProducts();
    List<ProductDto> getProductBySupplierName(String supplierName);
    SupplierProductDto getProductInfoBySupplierNameProductId(String supplierName, String productId);
    SupplierProductDto toDto(SupplierProduct supplierProduct);
    SupplierProduct toEntity(SupplierProductDto supplierProductDto);
    List<SupplierProductDto> getSupplierProductsByProductId(String productId);
}
