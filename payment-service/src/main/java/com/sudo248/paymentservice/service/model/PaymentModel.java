package com.sudo248.paymentservice.service.model;

import com.sudo248.paymentservice.repository.entity.PaymentStatus;
import lombok.Data;

@Data
public class PaymentModel {
    private String paymentId;
    private String orderId;
    private String orderType;
    private Double amount;
    private String bankCode;
    private String paymentType;
    private PaymentStatus status;

    public PaymentModel() {
    }

    public PaymentModel(String paymentId, String orderId, String orderType, Double amount, String bankCode, String paymentType, PaymentStatus status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.orderType = orderType;
        this.amount = amount;
        this.bankCode = bankCode;
        this.paymentType = paymentType;
        this.status = status;
    }
}
