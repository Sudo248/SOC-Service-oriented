package com.sudo248.soc.domain.common


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:08 - 12/03/2023
 */
object Constants {
    const val TIMEOUT_OTP = 30_000L
    const val PATTERN_OTP = "(|^)\\d{6}"

    object Key {
        const val PHONE_NUMBER = "PHONE_NUMBER"
        const val TOKEN = "TOKEN"
    }
}