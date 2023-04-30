package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.controller.dto.ProductDto;
import com.sudo248.discoveryservice.controller.dto.SupplierProductDto;
import com.sudo248.discoveryservice.repository.*;
import com.sudo248.discoveryservice.repository.entity.*;
import com.sudo248.discoveryservice.service.ImageService;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.domain.util.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryRepository categoryRepository;

    private final SupplierProductRepository supplierProductRepository;

    private final SupplierRepository supplierRepository;
    private final SupplierProductService supplierProductService;

    public ProductServiceImpl(ProductRepository productRepository, ImageService imageService, CategoryRepository categoryRepository, SupplierProductRepository supplierProductRepository, SupplierRepository supplierRepository, SupplierProductService supplierProductService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.categoryRepository = categoryRepository;
        this.supplierProductRepository = supplierProductRepository;
        this.supplierRepository = supplierRepository;
        this.supplierProductService = supplierProductService;
    }

    @Override
    public ProductDto addProduct(String userId, ProductDto productDto) {
        productDto.setSku(Utils.genSkuOrElse(productDto.getSku()));

        Product product = toEntity(productDto);
        product.setImages(productDto.getImages().stream().map(
                (imageDto -> new Image(
                        Utils.createIdOrElse(imageDto.getImageId()),
                        imageDto.getUrl(),
                        imageDto.getOwnerId(),
                        product
                ))).collect(Collectors.toList()));

        product.setSupplierProducts(productDto.getSupplierProducts().stream().map((
                supplierProductDto -> new SupplierProduct(
                        new SupplierProductId(
                                product.getProductId(),
                                supplierProductDto.getSupplierId()
                        ),
                        product,
                        supplierRepository.getReferenceById(supplierProductDto.getSupplierId()),
                        supplierProductDto.getAmountLeft(),
                        supplierProductDto.getPrice(),
                        supplierProductDto.getSoldAmount(),
                        supplierProductDto.getRate()
                )
        )).collect(Collectors.toList()));

        product.setCategories(productDto.getCategoryIds().stream().map(categoryRepository::getReferenceById)
                .collect(Collectors.toList()));

        productRepository.save(product);
        return toDto(userId, product);
    }

    @Override
    public ProductDto putProduct(String userId, ProductDto productDto) {
        Product oldProduct = productRepository.getReferenceById(productDto.getProductId());
        oldProduct.setName(productDto.getName());
        oldProduct.setDescription(productDto.getDescription());
        oldProduct.setCategories(productDto.getCategoryIds().stream().map(categoryRepository::getReferenceById)
                .collect(Collectors.toList()));
        oldProduct.setImages(productDto.getImages().stream().map(
                (imageDto -> new Image(
                        Utils.createIdOrElse(imageDto.getImageId()),
                        imageDto.getUrl(),
                        imageDto.getOwnerId(),
                        oldProduct
                ))).collect(Collectors.toList()));

        SupplierProductDto supplierProductDto = productDto.getSupplierProducts().get(0);

        SupplierProduct oldSupplierProduct = supplierProductRepository.getBySupplierProductId(new SupplierProductId(
                oldProduct.getProductId(),
                supplierProductDto.getSupplierId()
        ));

        oldSupplierProduct.setAmountLeft(supplierProductDto.getAmountLeft());
        oldSupplierProduct.setPrice(supplierProductDto.getPrice());

        supplierProductRepository.save(oldSupplierProduct);
        productRepository.save(oldProduct);

        return toDto(userId, oldProduct);
    }


    public ProductDto getProductById(String userId, String productId) {
        Product product = productRepository.getReferenceById(productId);
        return toDto(userId, product);
    }

    @Override
    public ProductDto toDto(String userId, Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setSku(product.getSku());
        productDto.setCategoryIds(product.getCategories().stream().map(Category::getCategoryId).collect(Collectors.toList()));
        productDto.setImages(product.getImages().stream().map(imageService::toDto).collect(Collectors.toList()));
        productDto.setSupplierProducts(product.getSupplierProducts().stream().map(supplierProduct -> supplierProductService.toDto(userId, supplierProduct)).collect(Collectors.toList()));
        return productDto;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(Utils.createIdOrElse(productDto.getProductId()));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());

        return product;
    }


    @Override
    public List<ProductDto> getAllProducts(String userId) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(c -> toDto(userId, c)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByName(String userId, String name) {
        List<Product> products = productRepository.findByNameContainsIgnoreCase(name);
        return products.stream().map(c -> toDto(userId, c)).collect(Collectors.toList());
    }
}
