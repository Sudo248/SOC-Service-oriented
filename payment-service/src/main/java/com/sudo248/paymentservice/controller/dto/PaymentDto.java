package com.sudo248.paymentservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String orderId;
    private String orderType;
    private String bankCode;
    private Double amount;
    private String paymentType;
    private String ipAddress;
}
