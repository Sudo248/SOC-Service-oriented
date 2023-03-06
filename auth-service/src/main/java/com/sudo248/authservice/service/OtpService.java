package com.sudo248.authservice.service;

import com.sudo248.authservice.contronller.dto.VerifyDto;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.base.BaseService;
import org.springframework.http.ResponseEntity;

public interface OtpService extends BaseService {
    ResponseEntity<BaseResponse<?>> generateOtp(String phoneNumber);
    ResponseEntity<BaseResponse<?>> verifyOtp(VerifyDto verifyDto);
}
