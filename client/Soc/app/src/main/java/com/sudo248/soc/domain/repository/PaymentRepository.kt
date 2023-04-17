package com.sudo248.soc.domain.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.soc.domain.entity.payment.Payment

interface PaymentRepository {
    suspend fun getPaymentUrl(payment: Payment): DataState<Payment, Exception>
}