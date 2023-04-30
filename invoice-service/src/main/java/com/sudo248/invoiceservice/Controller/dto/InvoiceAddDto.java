package com.sudo248.invoiceservice.Controller.dto;

import com.sudo248.invoiceservice.repository.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceAddDto {
    private String invoiceId;
    private String cartId;
    private String paymentId;
    private String promotionId;
    private OrderStatus status = OrderStatus.PREPARE;
    private Double totalPrice, totalPromotionPrice, finalPrice;
}
