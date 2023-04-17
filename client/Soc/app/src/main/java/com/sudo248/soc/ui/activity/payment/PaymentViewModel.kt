package com.sudo248.soc.ui.activity.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.soc.domain.entity.payment.Payment
import com.sudo248.soc.domain.entity.payment.PaymentType
import com.sudo248.soc.domain.repository.PaymentRepository
import com.sudo248.soc.ui.activity.auth.AuthActivity_GeneratedInjector
import com.sudo248.soc.ui.activity.splash.SplashActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
) : BaseViewModel<IntentDirections>() {

    lateinit var viewController: ViewController

    private val _currentPaymentType = MutableLiveData<PaymentType>()
    val currentPaymentType: LiveData<PaymentType> = _currentPaymentType

    var error: SingleEvent<String?> = SingleEvent(null)

    fun onSelectPaymentType(type: PaymentType) {
        _currentPaymentType.postValue(type)
    }

    fun onBack() {
        navigator.back()
    }

    fun onPayment() {
        if (_currentPaymentType.value == PaymentType.VN_PAY) {
            viewController.openVnPaySdk()
        }
    }

    suspend fun getVnPayPaymentUrl(): Deferred<String?> = async {
        val response = paymentRepository.getPaymentUrl(
            Payment(
                paymentType = PaymentType.VN_PAY,
                orderId = "Test order id"
            )
        )
        when(response) {
            is DataState.Success -> {
                _uiState.emit(UiState.SUCCESS)
                response.requireData().paymentUrl
            }
            else -> {
                error = SingleEvent(response.error().message)
                _uiState.emit(UiState.ERROR)
                null
            }
        }
    }
}