package com.sudo248.paymentservice.repository;

import com.sudo248.paymentservice.repository.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
