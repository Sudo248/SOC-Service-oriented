package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryProductDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.CategoryProductRepository;
import com.sudo248.discoveryservice.repository.entity.CategoryProduct;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.service.CategoryProductService;
import com.sudo248.discoveryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryProductServiceImpl implements CategoryProductService {
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Override
    public List<ProductDto> getProductByIdCategory(int id) {
        List<CategoryProduct> categoryProducts =  categoryProductRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(CategoryProduct s: categoryProducts){

            if(s.getCategory().getCategoryId() == id){
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
    public CategoryProductDto toDto(CategoryProduct categoryProduct) {
        return null;
    }

    @Override
    public CategoryProduct toEntity(CategoryProductDto categoryProductDto) {
        return null;
    }
}
