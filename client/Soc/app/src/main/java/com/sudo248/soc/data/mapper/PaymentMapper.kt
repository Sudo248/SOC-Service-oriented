package com.sudo248.soc.data.mapper

import com.sudo248.soc.data.dto.payment.PaymentDto
import com.sudo248.soc.domain.entity.payment.Payment
import com.sudo248.soc.domain.entity.payment.PaymentStatus
import com.sudo248.soc.domain.entity.payment.PaymentType

fun PaymentDto.toPayment(): Payment {
    return Payment(
        paymentId,
        paymentUrl,
        PaymentType.fromString(paymentType),
        bankCode,
        orderId,
        orderType,
        PaymentStatus.valueOf(paymentStatus),
        amount
    )
}