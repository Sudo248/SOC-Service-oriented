package com.sudo248.soc.domain.entity.invoice

data class Shipment(
    val nameShipment: String = "Nhanh",
    val timeShipment: Int = 0,
    val timeUnit: String = "",
    val priceShipment: Double = 0.0,
)
