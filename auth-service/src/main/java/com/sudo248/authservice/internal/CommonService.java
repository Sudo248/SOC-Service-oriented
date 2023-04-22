package com.sudo248.authservice.internal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "COMMON-SERVICE/internal")
@Service
public interface CommonService {
    @GetMapping("/token/generate/{userId}")
    String generateToken(@PathVariable("userId") String userId);

    @GetMapping("/token/generate-refresh/{token}")
    String generateRefreshToken(@PathVariable("token") String token);

    @GetMapping("/token/user-id/{token}")
    String getUserIdFromToken(@PathVariable("token") String token);
}
