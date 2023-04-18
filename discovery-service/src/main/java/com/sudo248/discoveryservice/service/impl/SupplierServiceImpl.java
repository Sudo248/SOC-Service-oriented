package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.SupplierRepository;
import com.sudo248.discoveryservice.repository.entity.Supplier;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.util.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierProductService supplierProductService;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierProductService supplierProductService) {
        this.supplierRepository = supplierRepository;
        this.supplierProductService = supplierProductService;
    }

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Supplier supplier = toEntity(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplier);
        supplierDto.setSupplierId(savedSupplier.getSupplierId());
        return supplierDto;
    }
    public SupplierDto getSupplierByName(String supplierName){
        List<Supplier> suppliers = supplierRepository.findAll();
        for(Supplier c: suppliers){
            if(c.getName().contains(supplierName)){
                return toDto(c);
            }
        }
        return null;
    }
    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public SupplierDto toDto(Supplier supplier){
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(supplier.getSupplierId());
        supplierDto.setName(supplier.getName());
        supplierDto.setAvatar(supplier.getAvatar());
        supplierDto.setLocation(supplier.getLocation());
        supplierDto.setSupplierProducts(supplier.getSupplierProducts().stream().map(supplierProductService::toDto).collect(Collectors.toList()));
        return supplierDto;
    }
    public Supplier toEntity(SupplierDto supplierDto){
        Supplier supplier = new Supplier();
        supplier.setSupplierId(Utils.createIdOrElse(supplierDto.getSupplierId()));
        supplier.setName(supplierDto.getName());
        supplier.setAvatar(supplierDto.getAvatar());
        supplier.setLocation(supplierDto.getLocation());
        return supplier;
    }
}
