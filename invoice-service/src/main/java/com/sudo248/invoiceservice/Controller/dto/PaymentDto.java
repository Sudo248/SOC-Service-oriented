package com.sudo248.invoiceservice.Controller.dto;

public class PaymentDto {
    private String paymentId;
    private String type;
    private Double value;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public PaymentDto(String paymentId) {
        this.paymentId = paymentId;
    }
}
