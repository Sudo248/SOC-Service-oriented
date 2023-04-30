package com.sudo248.paymentservice.controller.dto;

import com.sudo248.paymentservice.repository.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String paymentId;
    private String orderId;
    private String orderType;
    private String bankCode;
    private Double amount;
    private String paymentType;
    private String ipAddress;
    private String paymentUrl;
    private PaymentStatus paymentStatus;
}
