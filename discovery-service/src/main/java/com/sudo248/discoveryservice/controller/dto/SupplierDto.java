package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private String supplierId;
    private String name, avatar, location;
    private List<SupplierProductDto> supplierProducts;
}
