package com.sudo248.soc.data.api.discovery.request

data class PaymentRequest(
    val paymentType: String,
    val bankCode: String? = null,
    val orderId: String,
    val orderType: String = "100000",
    val ipAddress: String? = null,
)