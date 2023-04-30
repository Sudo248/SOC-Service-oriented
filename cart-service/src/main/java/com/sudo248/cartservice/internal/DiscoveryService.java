package com.sudo248.cartservice.internal;

import com.sudo248.cartservice.controller.dto.SupplierProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DISCOVERY-SERVICE")
@Service
public interface DiscoveryService {

    @GetMapping("/api/v1/discovery/service/supplierId/{supplierId}/productId/{productId}")
    SupplierProductDto getSupplierProduct(
            @PathVariable("supplierId") String supplierId,
            @PathVariable("productId") String productId
    );

    @GetMapping("/api/v1/discovery/service/supplierId/{supplierId}/productId/{productId}/price")
    Double getSupplierProductPrice(
            @PathVariable("supplierId") String supplierId,
            @PathVariable("productId") String productId
    );
}
