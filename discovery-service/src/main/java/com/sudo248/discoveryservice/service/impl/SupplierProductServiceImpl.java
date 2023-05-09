package com.sudo248.discoveryservice.service.impl;

import com.sudo248.discoveryservice.cache.CacheLocationManager;
import com.sudo248.discoveryservice.controller.dto.*;
import com.sudo248.discoveryservice.controller.dto.mapbox.MapBoxDistanceDto;
import com.sudo248.discoveryservice.controller.dto.mapbox.MapBoxRouteDto;
import com.sudo248.discoveryservice.external.MapBoxService;
import com.sudo248.discoveryservice.repository.SupplierProductRepository;
import com.sudo248.discoveryservice.repository.SupplierRepository;
import com.sudo248.discoveryservice.repository.entity.Location;
import com.sudo248.discoveryservice.repository.entity.Supplier;
import com.sudo248.discoveryservice.repository.entity.SupplierProduct;
import com.sudo248.discoveryservice.repository.entity.SupplierProductId;
import com.sudo248.discoveryservice.service.ProductService;
import com.sudo248.discoveryservice.service.SupplierProductService;
import com.sudo248.discoveryservice.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SupplierProductServiceImpl implements SupplierProductService {
    private final SupplierProductRepository supplierProductRepository;

    private final SupplierRepository supplierRepository;

    private final ProductService productService;

    private final CacheLocationManager cacheLocationManager;

    private final MapBoxService mapBoxService;

    private final SupplierService supplierService;

    @Lazy
    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository, SupplierRepository supplierRepository, ProductService productService, CacheLocationManager cacheLocationManager, MapBoxService mapBoxService, SupplierService supplierService) {
        this.supplierProductRepository = supplierProductRepository;
        this.supplierRepository = supplierRepository;
        this.productService = productService;
        this.cacheLocationManager = cacheLocationManager;
        this.mapBoxService = mapBoxService;
        this.supplierService = supplierService;
    }

    @Override
    public List<SupplierProductDto> getAllSupplierProducts(String userId) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        return SupplierProducts.stream().map(supplierProduct -> toDto(userId, supplierProduct)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductBySupplierName(String userId, String supplierName) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (SupplierProduct s : SupplierProducts) {
            if (s.getSupplier().getName().equals(supplierName)) {
                productDtos.add(productService.toDto(userId, s.getProduct()));
            }
        }
        return productDtos;
    }

    @Override
    public SupplierProductDto getProductInfoBySupplierNameProductId(String userId, String supplierName, String productId) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        for (SupplierProduct s : SupplierProducts) {
            if (s.getProduct().getProductId().equals(productId) && s.getSupplier().getName().equals(supplierName)) {
                return toDto(userId, s);
            }
        }
        return null;
    }

    @Override
    public SupplierProductCartDto getProductInfoBySupplierIdProductId(String userId, String supplierId, String productId, boolean hasRoute) {
        SupplierProduct supplierProduct = supplierProductRepository.getReferenceById(new SupplierProductId(productId, supplierId));
        SupplierProductCartDto supplierProductCartDto = new SupplierProductCartDto();
        supplierProductCartDto.setProduct(productService.getRawProductById(productId));
        supplierProductCartDto.setSupplier(supplierService.getSupplierById(supplierId));
        supplierProductCartDto.setRoute(hasRoute ? getRouteMapBox(userId, supplierId) : new RouteDto());
        supplierProductCartDto.setAmountLeft(supplierProduct.getAmountLeft());
        supplierProductCartDto.setPrice(supplierProduct.getPrice());
        supplierProductCartDto.setRate(supplierProduct.getRate());
        supplierProductCartDto.setSoldAmount(supplierProduct.getSoldAmount());
        return supplierProductCartDto;
    }

    @Override
    public List<SupplierProductInfoDto> getAllSupplierProductInfo(String userId) {
        Supplier supplier = supplierRepository.getSupplierByUserId(userId);
        List<SupplierProduct> supplierProducts = supplier.getSupplierProducts();
        return supplierProducts.stream().map((supplierProduct -> new SupplierProductInfoDto(
                supplier.getSupplierId(),
                supplierProduct.getProduct().getProductId(),
                supplierProduct.getProduct().getCategories().get(0).getCategoryId(),
                supplierProduct.getProduct().getImages().stream().map(image -> new ImageDto(image.getImageId(), image.getUrl(), image.getOwnerId())).collect(Collectors.toList()),
                supplierProduct.getProduct().getDescription(),
                supplierProduct.getProduct().getName(),
                supplierProduct.getProduct().getCategories().get(0).getName(),
                supplierProduct.getAmountLeft(),
                supplierProduct.getPrice(),
                supplierProduct.getSoldAmount(),
                supplierProduct.getRate(),
                supplierProduct.getProduct().getSku()
        ))).collect(Collectors.toList());
    }

    @Override
    public List<SupplierProductDto> getSupplierProductsByProductId(String userId, String productId) {
        List<SupplierProduct> SupplierProducts = supplierProductRepository.findAll();
        List<SupplierProductDto> supplierProductDtos = new ArrayList<>();
        for (SupplierProduct s : SupplierProducts) {
            if (s.getProduct().getProductId().equals(productId)) {
                supplierProductDtos.add(toDto(userId, s));
            }
        }
        return supplierProductDtos;
    }

    @Override
    public Double getProductPrice(String supplierId, String productId) {
        SupplierProduct supplierProduct = supplierProductRepository.getReferenceById(new SupplierProductId(productId, supplierId));
        return supplierProduct.getPrice();
    }

    @Override
    public SupplierProductDto orderProduct(String userId, String productId, String supplierId, int amount) {
        SupplierProduct supplierProduct = supplierProductRepository.getReferenceById(new SupplierProductId(productId, supplierId));
        int amountLeft = supplierProduct.getAmountLeft();
        int soldAmount = supplierProduct.getSoldAmount();

        supplierProduct.setAmountLeft(amountLeft - amount);
        supplierProduct.setSoldAmount(soldAmount + amount);

        supplierProductRepository.save(supplierProduct);
        return toDto(userId, supplierProduct);
    }

    @Override
    public SupplierProductDto toDto(String userId, SupplierProduct s) {
        boolean isSameLocation = userId == null || userId.equals(s.getSupplier().getUserId());
        return new SupplierProductDto(
                s.getSupplier().getSupplierId(),
                s.getProduct().getProductId(),
                isSameLocation ? new RouteDto() : getRouteMapBox(userId, s.getSupplier().getSupplierId()),
                s.getAmountLeft(),
                s.getPrice(),
                s.getSoldAmount(),
                s.getRate()
        );
    }

    @Override
    public SupplierProduct toEntity(SupplierProductDto s) {
        //Waiting for other APIs
        return new SupplierProduct();
    }

    private RouteDto getRouteMapBox(String userId, String supplierId) {
        Supplier supplier = supplierRepository.getRawSupplierById(supplierId);
        RouteDto routeDto;
        try {
            routeDto = getRouteMapBox(cacheLocationManager.getLocation(userId), cacheLocationManager.getLocation(supplier.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
            cacheLocationManager.requiredGetLocation(supplier.getUserId());
            try {
                routeDto = getRouteMapBox(cacheLocationManager.getLocation(userId), cacheLocationManager.getLocation(supplier.getUserId()));
            } catch (Exception ex) {
                ex.printStackTrace();
                routeDto = new RouteDto();
            }
        }
        return routeDto;
    }

    private RouteDto getRouteMapBox(Location from, Location to) throws Exception {
        MapBoxDistanceDto mapBoxDistanceDto = mapBoxService.getDistance(from.getLongitude(), from.getLatitude(), to.getLongitude(), to.getLatitude());
        MapBoxRouteDto mapBoxRouteDto = mapBoxDistanceDto.getRoutes().get(0);
        log.info("Sudoo: " + mapBoxRouteDto.getDistance() + " " + mapBoxRouteDto.getDuration());
        return new RouteDto(
                mapBoxRouteDto.getWeight(),
                getDurationValue(mapBoxRouteDto.getDuration()),
                getDistanceValue(mapBoxRouteDto.getDistance())
        );
    }

    private ValueDto getDurationValue(double duration) {
        if (duration < 60) return new ValueDto(duration, "giây");
        double minutes = duration / 60;
        if (minutes < 60) return new ValueDto(minutes, "phút");
        double hour = minutes / 60;
        if (hour < 24) return new ValueDto(hour, "giờ");
        return new ValueDto(hour / 24, "ngày");
    }

    private ValueDto getDistanceValue(double distance) {
        if (distance < 500) return new ValueDto(distance, "m");
        return new ValueDto(distance / 1000, "km");
    }
}
