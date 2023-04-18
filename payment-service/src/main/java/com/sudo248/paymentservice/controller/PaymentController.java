package com.sudo248.paymentservice.controller;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.paymentservice.controller.dto.PaymentDto;
import com.sudo248.paymentservice.service.IpService;
import com.sudo248.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final IpService ipService;

    public PaymentController(PaymentService paymentService, IpService ipService) {
        this.paymentService = paymentService;
        this.ipService = ipService;
    }

    @PostMapping("/pay")
    public ResponseEntity<BaseResponse<?>> pay(@RequestBody PaymentDto paymentDto, HttpServletRequest request) {
        if (paymentDto.getIpAddress() == null) paymentDto.setIpAddress(ipService.getIpAddress(request));
        return paymentService.pay(paymentDto);
    }
}
