package com.sudo248.cartservice.internal;

import com.sudo248.cartservice.controller.dto.SupplierProductDto;
import com.sudo248.domain.common.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "DISCOVERY-SERVICE")
@Service
public interface DiscoveryService {

    @GetMapping("/api/v1/discovery/service/supplierId/{supplierId}/productId/{productId}")
    SupplierProductDto getSupplierProduct(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("supplierId") String supplierId,
            @PathVariable("productId") String productId,
            @RequestParam("hasRoute") boolean hasRoute
    );

    @GetMapping("/api/v1/discovery/service/supplierId/{supplierId}/productId/{productId}/price")
    Double getSupplierProductPrice(
            @PathVariable("supplierId") String supplierId,
            @PathVariable("productId") String productId
    );
}
