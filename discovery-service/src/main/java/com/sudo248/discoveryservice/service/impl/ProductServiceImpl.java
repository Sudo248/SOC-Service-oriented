package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.CategoryDto;
import com.sudo248.discoveryservice.controller.dto.ImageDto;
import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.repository.CategoryRepository;
import com.sudo248.discoveryservice.repository.ImageRepository;
import com.sudo248.discoveryservice.repository.ProductRepository;
import com.sudo248.discoveryservice.repository.entity.Category;
import com.sudo248.discoveryservice.repository.entity.Image;
import com.sudo248.discoveryservice.repository.entity.Product;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.discoveryservice.service.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository ProductRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierProductService supplierProductService;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = toEntity(productDto);
        Product savedProduct = ProductRepository.save(product);
        productDto.setProductId(savedProduct.getProductId());
        return productDto;
    }



    public ProductDto getProductById(String id){
        List<Product> products = ProductRepository.findAll();
        for(Product c: products){
            if(c.getProductId().equals(id)){
               return toDto(c);

            }
        }
        return null;
    }

    @Override
    public ProductDto toDto(Product c) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(c.getProductId());
        productDto.setName(c.getName());
        productDto.setDescription(c.getDescription());
        productDto.setSku(c.getSku());
        productDto.setSupplierProducts(supplierProductService.getSupplierProductsByProductId(c.getProductId()));
        return productDto;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());

        return product;
    }


    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = ProductRepository.findAll();
        return products.stream().map(Product -> {
            return toDto(Product);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByName(String name) {
        List<Product> products = ProductRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product p: products){
            if(p.getName().toLowerCase().contains(name.toLowerCase()))
                productDtos.add(toDto(p));
        }
        return productDtos;
    }


}
