package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.cache.CacheLocationManager;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheLocationManager cacheLocationManager;

    public CacheController(CacheLocationManager cacheLocationManager) {
        this.cacheLocationManager = cacheLocationManager;
    }

    @GetMapping()
    ResponseEntity<BaseResponse<?>> getCache() {
        return BaseResponse.ok(cacheLocationManager.getAllLocation());
    }

    @GetMapping("/clear")
    ResponseEntity<BaseResponse<?>> clearCache() {
        return BaseResponse.ok(cacheLocationManager.clearCache());
    }

}
