package com.sudo248.invoiceservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    private String invoiceId;

    @Column(name = "promotion_id")
    private String promotionId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "cart_id")
    private String cartId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "totalPromotionPrice")
    private Double totalPromotionPrice;

    @Column(name = "finalPrice")
    private Double finalPrice;
}
