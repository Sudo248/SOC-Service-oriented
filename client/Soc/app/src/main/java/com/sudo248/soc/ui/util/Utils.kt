package com.sudo248.soc.ui.util

import com.sudo248.soc.domain.ktx.format
import java.text.NumberFormat
import java.util.Locale

object Utils {
    private val locale = Locale("vi", "VN")

    fun format(value: Double, digit: Int): String {
        return value.format(digit)
    }

    fun formatVnCurrency(value: Double): String {
        return NumberFormat.getCurrencyInstance(locale).format(value)
    }

    fun formatShortSold(value: Double): String {
        return if (value >= 100) {
            " . 100+"
        } else {
            " . ${value.toInt()}+"
        }
    }

    fun formatSold(value: Double): String {
        return "Đã bán: ${
            if (value >= 100) {
                "100+"
            } else {
                "${value.toInt()}+"
            }
        }"
    }

    fun formatHideInfo(value: String, numberPlainText: Int = 0): String {
        return buildString {
            val _numberPlainText =
                if (numberPlainText < 0) 0 else if (numberPlainText > value.length) value.length else numberPlainText
            val hideLength = value.length - _numberPlainText
            repeat(hideLength) {
                this.append('*')
            }
            append(value.substring(hideLength))
        }
    }
}