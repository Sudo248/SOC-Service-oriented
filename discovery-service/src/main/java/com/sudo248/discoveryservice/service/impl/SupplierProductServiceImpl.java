package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.repository.SupplierProductRepository;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.discoveryservice.service.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class SupplierProductServiceImpl implements SupplierProductService {
    private final SupplierProductRepository supplierProductRepository;

    private final ProductService productService;

    @Lazy
    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository, ProductService productService) {
        this.supplierProductRepository = supplierProductRepository;
        this.productService = productService;
    }

    @Override
    public List<SupplierProductDto> getAllSupplierProducts() {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        return SupplierProducts.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductBySupplierName(String supplierName) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(SupplierProduct s: SupplierProducts){

            if(s.getSupplier().getName().equals(supplierName)){
                productDtos.add(productService.toDto(s.getProduct()));
            }

        }
        return productDtos;
    }

    @Override
    public SupplierProductDto getProductInfoBySupplierNameProductId(String supplierName, String productId) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        for(SupplierProduct s: SupplierProducts){
            if(s.getProduct().getProductId().equals(productId) && s.getSupplier().getName().equals(supplierName)){
                return toDto(s);
            }
        }
        return null;
    }
    @Override
    public List<SupplierProductDto> getSupplierProductsByProductId(String productId) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        List<SupplierProductDto> supplierProductDtos = new ArrayList<>();
        for(SupplierProduct s: SupplierProducts){
            if(s.getProduct().getProductId().equals(productId)){
                supplierProductDtos.add(toDto(s));
            }
        }
        return supplierProductDtos;
    }
    @Override
    public SupplierProductDto toDto(SupplierProduct s) {
        return new SupplierProductDto(
                s.getSupplier().getSupplierId(),
                s.getProduct().getProductId(),
                calcDistance(s.getSupplier().getLocation()),
                s.getAmountLeft(),s.getPrice(),
                s.getSoldAmount(),s.getRate()
        );
    }

    @Override
    public SupplierProduct toEntity(SupplierProductDto s) {
        //Waiting for other APIs
        return new SupplierProduct();
    }



    private Double calcDistance(String locationSupplier){
        String[] lonlatSup = locationSupplier.split(",");
        Double lonSup = Double.parseDouble(lonlatSup[0]);
        Double latSup = Double.parseDouble(lonlatSup[1]);
        String lonlat = "20.97,105.85";
        String[] lonlatUser = lonlat.split(",");

        Double lonUser = Double.parseDouble(lonlatUser[0]);
        Double latUser = Double.parseDouble(lonlatUser[1]);
        return Math.sqrt( (lonSup - lonUser) * (lonSup - lonUser) + (latSup - latUser) * (latSup - latUser));
    }
}
