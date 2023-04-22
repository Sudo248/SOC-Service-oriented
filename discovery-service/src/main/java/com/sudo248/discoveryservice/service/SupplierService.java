package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.entity.Supplier;

import java.util.List;

public interface SupplierService {
    SupplierDto addSupplier(String userId, SupplierDto supplierDto);

    List<SupplierDto> getAllSuppliers(String userId);
    SupplierDto getSupplierByName(String userId, String supplierName);

    SupplierDto getSupplierByUserId(String userId, boolean isDetail);

    SupplierDto toDto(String userId, Supplier supplier);
    Supplier toEntity(String userId, SupplierDto supplierDto);
}