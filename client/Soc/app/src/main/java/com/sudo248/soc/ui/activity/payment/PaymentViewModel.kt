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
import com.sudo248.soc.domain.common.Constants
import com.sudo248.soc.domain.entity.payment.Payment
import com.sudo248.soc.domain.entity.payment.PaymentStatus
import com.sudo248.soc.domain.entity.payment.PaymentType
import com.sudo248.soc.domain.repository.PaymentRepository
import com.sudo248.soc.domain.repository.UserRepository
import com.sudo248.soc.ui.activity.auth.AuthActivity_GeneratedInjector
import com.sudo248.soc.ui.activity.splash.SplashActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val userRepository: UserRepository
) : BaseViewModel<IntentDirections>() {

    lateinit var viewController: ViewController

    private val _userInfo = MutableLiveData<String>()
    val userInfo: LiveData<String> = _userInfo

    private val _currentPaymentType = MutableLiveData<PaymentType>()
    val currentPaymentType: LiveData<PaymentType> = _currentPaymentType

    var error: SingleEvent<String?> = SingleEvent(null)

    init {
        getUserInfo()
    }

    fun onSelectPaymentType(type: PaymentType) {
        _currentPaymentType.postValue(type)
    }

    fun onBack() {
        navigator.back()
    }

    fun onPayment() {
        if (_currentPaymentType.value == PaymentType.VN_PAY) {
            viewController.openVnPaySdk()
        } else {
            payWithCOD()
        }
    }

    private fun getUserInfo() = launch {
        userRepository.getUserInfo()
            .onSuccess { user ->
                val info = "${user.fullName} | ${user.phone}\n${user.address.fullAddress}"
                _userInfo.postValue(info)
            }
            .onError {
                viewController.toast(it.message ?: Constants.UNKNOWN_ERROR)
            }
    }

    suspend fun getVnPayPaymentUrl(): Deferred<String?> = async {
        val response = paymentRepository.getPaymentUrl(
            Payment(
                paymentType = PaymentType.VN_PAY,
                orderId = "Test order id",
                paymentStatus = PaymentStatus.INIT,
                amount = 10000.0
            )
        )
        when(response) {
            is DataState.Success -> {
                emitState(UiState.SUCCESS)
                response.requireData().paymentUrl
            }
            else -> {
                error = SingleEvent(response.error().message)
                emitState(UiState.ERROR)
                null
            }
        }
    }

    fun payWithCOD() = launch {
        setState(UiState.LOADING)
        paymentRepository.getPaymentUrl(
            Payment(
                paymentType = PaymentType.CASH,
                orderId = "Test order id",
                paymentStatus = PaymentStatus.INIT,
                amount = 10000.0
            )
        )
            .onSuccess {
                setState(UiState.SUCCESS)
                viewController.payWithCODSuccess()
            }
            .onError {
                error = SingleEvent(it.message)
                setState(UiState.ERROR)
            }
    }
}