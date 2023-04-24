package com.sudo248.cartservice.service;

import com.sudo248.cartservice.controller.dto.AddSupplierProductDto;
import com.sudo248.cartservice.controller.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartSupplierProductService {
    CartDto addSupplierProductToCart(String userId, AddSupplierProductDto addSupplierProductDto);
    CartDto updateSupplierProductToCart(String userId, List<AddSupplierProductDto> addSupplierProductDtoList);
}
