package com.sudo248.soc.domain.ktx

fun Double.format(digit: Int = 1):String {
    return String.format("%.${digit}f", this)
}