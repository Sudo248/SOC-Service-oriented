package com.sudo248.paymentservice.service;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.base.BaseService;
import com.sudo248.paymentservice.controller.dto.PaymentDto;
import com.sudo248.paymentservice.controller.dto.PaymentInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface PaymentService extends BaseService {
    ResponseEntity<BaseResponse<?>> pay(PaymentDto paymentDto);

    PaymentInfoDto getPaymentInfo(String paymentId);
}
