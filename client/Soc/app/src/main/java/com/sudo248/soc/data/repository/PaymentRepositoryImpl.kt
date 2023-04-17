package com.sudo248.soc.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc.data.api.discovery.request.PaymentRequest
import com.sudo248.soc.data.api.payment.PaymentService
import com.sudo248.soc.data.ktx.errorBody
import com.sudo248.soc.data.mapper.toPayment
import com.sudo248.soc.domain.entity.payment.Payment
import com.sudo248.soc.domain.repository.PaymentRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor(
    private val paymentService: PaymentService,
    private val ioDispatcher: CoroutineDispatcher,
) : PaymentRepository {
    override suspend fun getPaymentUrl(payment: Payment): DataState<Payment, Exception> = stateOn(ioDispatcher) {
        val request = PaymentRequest(
            paymentType = payment.paymentType.value,
            bankCode = payment.bankCode,
            orderId = payment.orderId,
            orderType = payment.orderType,
        )
        val response = handleResponse(paymentService.pay(request))
        if (response.isSuccess) {
            response.get().data!!.toPayment()
        } else {
            throw response.error().errorBody()
        }
    }
}