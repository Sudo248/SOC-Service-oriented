package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.cache.CacheLocationManager;
import com.sudo248.discoveryservice.controller.dto.AddressDto;
import com.sudo248.discoveryservice.controller.dto.SupplierDto;
import com.sudo248.discoveryservice.internal.UserService;
import com.sudo248.discoveryservice.repository.SupplierRepository;
import com.sudo248.discoveryservice.repository.entity.Supplier;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.discoveryservice.service.SupplierService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierProductService supplierProductService;

    private final UserService userService;

    private final CacheLocationManager cacheLocationManager;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierProductService supplierProductService, UserService userService, CacheLocationManager cacheLocationManager) {
        this.supplierRepository = supplierRepository;
        this.supplierProductService = supplierProductService;
        this.userService = userService;
        this.cacheLocationManager = cacheLocationManager;
    }

    @Override
    public SupplierDto addSupplier(String userId, SupplierDto supplierDto) {
        Supplier supplier = toEntity(userId, supplierDto);
        Supplier savedSupplier = supplierRepository.save(supplier);
        supplierDto.setSupplierId(savedSupplier.getSupplierId());
        supplierDto.setLocation(cacheLocationManager.getLocation(supplier.getUserId()));
        return supplierDto;
    }
    public SupplierDto getSupplierByName(String userId, String supplierName){
        List<Supplier> suppliers = supplierRepository.findAll();
        for(Supplier c: suppliers){
            if(c.getName().contains(supplierName)){
                return toDto(userId, c);
            }
        }
        return null;
    }

    @Override
    public SupplierDto getSupplierByUserId(String userId, boolean isDetail) {
        if (isDetail) {
            Supplier supplier = supplierRepository.getSupplierByUserId(userId);
            return toDto(userId, supplier);
        } else {
            Supplier supplier = supplierRepository.getRawSupplierByUserId(userId);
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setSupplierId(supplier.getSupplierId());
            supplierDto.setAvatar(supplier.getAvatar());
            supplierDto.setName(supplier.getName());
            supplier.setLocation(cacheLocationManager.getLocation(userId));
            return supplierDto;
        }
    }

    @Override
    public ResponseEntity<BaseResponse<?>> getSupplierAddress(String supplierId) {
        Supplier supplier = supplierRepository.getRawSupplierById(supplierId);
        ResponseEntity<BaseResponse<?>> response = userService.getAddressSupplier(supplier.getUserId());
        return response;
    }

    @Override
    public List<SupplierDto> getAllSuppliers(String userId) {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(supplier -> toDto(userId, supplier)).collect(Collectors.toList());
    }

    public SupplierDto toDto(String userId, Supplier supplier){
        supplier.setLocation(cacheLocationManager.getLocation(supplier.getUserId()));
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(supplier.getSupplierId());
        supplierDto.setName(supplier.getName());
        supplierDto.setAvatar(supplier.getAvatar());
        supplierDto.setLocation(supplier.getLocation());
        supplierDto.setSupplierProducts(supplier.getSupplierProducts().stream().map(supplierProduct -> supplierProductService.toDto(userId, supplierProduct)).collect(Collectors.toList()));
        return supplierDto;
    }

    public Supplier toEntity(String userId, SupplierDto supplierDto){
        Supplier supplier = new Supplier();
        supplier.setSupplierId(Utils.createIdOrElse(supplierDto.getSupplierId()));
        supplier.setName(supplierDto.getName());
        supplier.setAvatar(supplierDto.getAvatar());
        supplier.setUserId(userId);
        return supplier;
    }

    @Override
    public SupplierDto getSupplierById(String id) {
        Supplier supplier = supplierRepository.getReferenceById(id);
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(supplier.getSupplierId());
        supplierDto.setName(supplier.getName());
        supplierDto.setAvatar(supplier.getAvatar());
        supplierDto.setLocation(supplier.getLocation());
        supplierDto.setSupplierProducts(new ArrayList<>());
        return supplierDto;
    }
}
