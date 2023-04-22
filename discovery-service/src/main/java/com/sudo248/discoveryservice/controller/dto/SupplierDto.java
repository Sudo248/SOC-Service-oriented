package com.sudo248.discoveryservice.controller.dto;

import com.sudo248.discoveryservice.repository.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private String supplierId;
    private String name, avatar;
    private Location location;
    private List<SupplierProductDto> supplierProducts;
}
