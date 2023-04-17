package com.sudo248.soc.domain.entity.payment

data class Payment(
    val paymentId: String = "",
    val paymentUrl: String? = null,
    val paymentType: PaymentType,
    val bankCode: String? = null,
    val orderId: String,
    val orderType: String = "100000",
)