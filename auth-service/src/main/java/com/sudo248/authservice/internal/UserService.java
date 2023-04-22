package com.sudo248.authservice.internal;

import com.sudo248.authservice.contronller.dto.UserDto;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "USER-SERVICE")
@Service
public interface UserService {
    @PostMapping("/api/v1/user/")
    ResponseEntity<BaseResponse<?>> createUser(@RequestBody UserDto userDto);
}
