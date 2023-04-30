package com.sudo248.invoiceservice.Controller.dto;

import com.sudo248.invoiceservice.repository.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private String invoiceId;
    private CartDto cart;
    private PaymentDto payment;
    private PromotionDto promotion;
    private UserDto user;
    private OrderStatus status;
    private Double totalPrice, totalPromotionPrice, finalPrice;
}
