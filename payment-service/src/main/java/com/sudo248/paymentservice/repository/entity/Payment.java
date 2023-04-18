package com.sudo248.paymentservice.repository.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_type")
    private String orderType;

    private Double amount;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "payment_type")
    private String paymentType;

    private PaymentStatus status;
}
