package com.sudo248.soc.data.mapper

import com.sudo248.soc.data.dto.invoice.InvoiceDto
import com.sudo248.soc.domain.entity.invoice.Invoice

fun InvoiceDto.toInvoice(): Invoice {
    return Invoice(
        invoiceId = invoiceId,
        cart = cart.toCart(),
        payment = payment?.toPayment(),
        promotion = promotion?.toPromotion(),
        user = user.toUser(),
        status = status,
        shipment = shipment,
        totalPrice,
        totalPromotionPrice,
        finalPrice
    )
}