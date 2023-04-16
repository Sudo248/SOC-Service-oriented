package com.sudo248.paymentservice.controller.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String orderId;
    private String orderType;
    private String bankCode;
    private String paymentType;
    private String ipAddress;
}
