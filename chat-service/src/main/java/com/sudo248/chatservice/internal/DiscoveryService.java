package com.sudo248.chatservice.internal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DISCOVERY-SERVICE")
@Service
public interface DiscoveryService {
    @GetMapping("/api/v1/discovery/supplier/internal/user/{supplierId}")
    String getUserIdBySupplierId(@PathVariable("supplierId") String supplierId);
}
