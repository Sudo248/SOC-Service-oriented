package com.sudo248.discoveryservice.internal;

import com.sudo248.discoveryservice.controller.dto.AddressDto;
import com.sudo248.discoveryservice.repository.entity.Location;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "USER-SERVICE")
@Service
public interface UserService {

    @GetMapping("/api/v1/user/address")
    ResponseEntity<BaseResponse<AddressDto>> getAddressSupplier(@RequestHeader(Constants.HEADER_USER_ID) String userId);

    @GetMapping("/api/v1/user/address/internal/location")
    Location getLocation(@RequestHeader(Constants.HEADER_USER_ID) String userId);

}
