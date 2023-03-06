package com.sudo248.authservice.contronller;

import com.sudo248.authservice.contronller.dto.VerifyDto;
import com.sudo248.authservice.service.OtpService;
import com.sudo248.domain.base.BaseResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OtpController {
    private final OtpService otpService;

    public OtpController(@Qualifier("TwilioOtpService") OtpService otpService) {
        this.otpService = otpService;
    }

    @GetMapping("/generate-otp/{phoneNumber}")
    public ResponseEntity<BaseResponse<?>> generateOtp(@PathVariable String phoneNumber) {
        return otpService.generateOtp(phoneNumber);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<BaseResponse<?>> verifyOtp(@RequestBody VerifyDto verifyDto) {
        return otpService.verifyOtp(verifyDto);
    }
}
