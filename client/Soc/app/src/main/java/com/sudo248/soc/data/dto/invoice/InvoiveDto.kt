package com.sudo248.soc.data.dto.invoice

import com.sudo248.soc.data.dto.cart.CartDto
import com.sudo248.soc.data.dto.payment.PaymentDto
import com.sudo248.soc.data.dto.promotion.PromotionDto
import com.sudo248.soc.data.dto.user.UserDto
import com.sudo248.soc.domain.entity.cart.Cart
import com.sudo248.soc.domain.entity.invoice.OrderStatus
import com.sudo248.soc.domain.entity.invoice.Shipment
import com.sudo248.soc.domain.entity.payment.Payment
import com.sudo248.soc.domain.entity.promotion.Promotion
import com.sudo248.soc.domain.entity.user.User

data class InvoiceDto(
    val invoiceId: String,
    val cart: CartDto,
    val payment: PaymentDto? = null,
    val promotion: PromotionDto? = null,
    val user: UserDto,
    val status: OrderStatus,
    val shipment: Shipment,
    val totalPrice: Double,
    val totalPromotionPrice: Double,
    val finalPrice: Double,
)