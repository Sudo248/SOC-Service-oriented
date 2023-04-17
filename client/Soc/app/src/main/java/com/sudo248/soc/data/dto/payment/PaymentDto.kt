package com.sudo248.soc.data.dto.payment

data class PaymentDto(
    val paymentId: String = "",
    val paymentUrl: String? = null,
    val paymentType: String,
    val bankCode: String? = null,
    val orderId: String,
    val orderType: String = "100000",
)
