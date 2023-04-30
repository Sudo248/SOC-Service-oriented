package com.sudo248.soc.domain.entity.discovery

data class Route(
    val weight: Double = 0.0,
    val duration: Value = Value(),
    val distance: Value = Value()
)
