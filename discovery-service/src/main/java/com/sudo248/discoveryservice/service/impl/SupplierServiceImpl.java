package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.repository.SupplierRepository;
import com.sudo248.discoveryservice.repository.entity.Supplier;
import com.sudo248.discoveryservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Supplier supplier = toEntity(supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplier);
        supplierDto.setSupplierId(savedSupplier.getSupplierId());
        return supplierDto;
    }
    public SupplierDto getSupplierByName(String name){
        List<Supplier> suppliers = supplierRepository.findAll();
        for(Supplier c: suppliers){
            if(c.getName().contains(name)){
                return toDto(c);
            }
        }
        return null;
    }
    @Override
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(Supplier -> {
            return toDto(Supplier);
        }).collect(Collectors.toList());
    }

    public SupplierDto toDto(Supplier supplier){
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(supplier.getSupplierId());
        supplierDto.setName(supplier.getName());
        supplierDto.setAvatar(supplier.getAvatar());
        return supplierDto;
    }
    public Supplier toEntity(SupplierDto supplierDto){
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDto.getSupplierId());
        supplier.setName(supplierDto.getName());
        supplier.setAvatar(supplierDto.getAvatar());
        supplier.setLocation(supplierDto.getLocation());
        return supplier;
    }
}
