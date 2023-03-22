package com.sudo248.discoveryservice.service;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.entity.Supplier;

import java.util.List;

public interface SupplierService {
    SupplierDto addSupplier(SupplierDto supplierDto);

    List<SupplierDto> getAllSuppliers();
    SupplierDto getSupplierByName(String name);
    SupplierDto toDto(Supplier supplier);
    Supplier toEntity(SupplierDto supplierDto);

    interface SupplierProductService {
    }
}
