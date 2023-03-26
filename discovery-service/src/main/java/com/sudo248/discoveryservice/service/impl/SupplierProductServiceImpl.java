package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.repository.SupplierProductRepository;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.service.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class SupplierProductServiceImpl implements SupplierProductService {
    @Autowired
    private SupplierProductRepository supplierProductRepository;
    @Override
    public List<SupplierProductDto> getAllSupplierProducts() {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        return SupplierProducts.stream().map(SupplierProduct -> {
            return toDto(SupplierProduct);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductBySupplierName(String name) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(SupplierProduct s: SupplierProducts){

            if(s.getSupplier().getName().equals(name)){
                ProductDto productDto = new ProductDto();
                productDto.setProductId(s.getProduct().getProductId());
                productDto.setName(s.getProduct().getName());
                productDto.setDescription(s.getProduct().getDescription());
                productDto.setSku(s.getProduct().getSku());
                productDtos.add(productDto);
            }

        }
        return productDtos;
    }

    @Override
    public SupplierProductDto getProductInfoBySupplierNameProductId(String name, int id) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        for(SupplierProduct s: SupplierProducts){
            if(s.getProduct().getProductId() == id && s.getSupplier().getName().equals(name)){
                return toDto(s);
            }
        }
        return null;
    }
    @Override
    public List<SupplierProductDto> getSupplierProductsByProductId(int idProduct) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        List<SupplierProductDto> supplierProductDtos = new ArrayList<>();
        for(SupplierProduct s: SupplierProducts){
            if(s.getProduct().getProductId() == idProduct ){
                supplierProductDtos.add(toDto(s));
            }
        }
        return supplierProductDtos;
    }
    @Override
    public SupplierProductDto toDto(SupplierProduct s) {
        SupplierProductDto spd = new SupplierProductDto(s.getSupplier().getSupplierId(),s.getProduct().getProductId(),calcDistance(s.getSupplier().getLocation()),s.getAmountLeft(),s.getPrice()
                ,s.getSoldAmount(),s.getRate());
        return spd;
    }

    @Override
    public SupplierProduct toEntity(SupplierProductDto s) {
        SupplierProduct supplierProduct = new SupplierProduct();
        //Waiting for other APIs
        return supplierProduct;
    }



    private Double calcDistance(String locationSupplier){
        String[] lonlatSup = locationSupplier.split(",");
        Double lonSup = Double.parseDouble(lonlatSup[0]);
        Double latSup = Double.parseDouble(lonlatSup[1]);
        String lonlat = "20.97,105.85";
        String[] lonlatUser = lonlat.split(",");

        Double lonUser = Double.parseDouble(lonlatUser[0]);
        Double latUser = Double.parseDouble(lonlatUser[1]);
        Double res = 0.0;
        res = Math.sqrt( (lonSup - lonUser) * (lonSup - lonUser) + (latSup - latUser) * (latSup - latUser));
        return res;
    }
}
