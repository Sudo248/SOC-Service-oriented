package com.sudo248.soc.ui.activity.otp

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.phone.SmsRetrieverClient
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.onState
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.base_android.utils.DateUtils
import com.sudo248.soc.domain.repository.AuthRepository
import com.sudo248.soc.domain.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:12 - 06/03/2023
 */
@HiltViewModel
class OtpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<IntentDirections>() {

    var errorMessage: String? = null

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _otp = MutableLiveData("")
    val otp: LiveData<String> = _otp

    private val _timeout = MutableLiveData<String>()
    val timeout: LiveData<String> = _timeout

    private val countDownTimer = object : CountDownTimer(Constants.TIMEOUT_OTP, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _timeout.postValue(DateUtils.format_mm_ss(millisUntilFinished))
        }

        override fun onFinish() {
            _timeout.postValue("timeout")
        }
    }

    private lateinit var smsRetrieverClient: SmsRetrieverClient


    fun setSmsRetrieverClient(client: SmsRetrieverClient) {
        this.smsRetrieverClient = client
    }

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.postValue(phoneNumber)
        resendOtp()
    }

    fun onFullFillListener(otp: String) = launch {
        stopTimer()
        setState(UiState.LOADING)
        authRepository.verifyOtp(_phoneNumber.value!!, otp).onState(
            onSuccess = {
                authRepository.saveToken(it.token)
                setState(UiState.SUCCESS)
            },
            onError = {
                errorMessage = it.message
                setState(UiState.ERROR)
            }
        )
    }

    fun resendOtp() = launch {
        _otp.postValue("")
//        setState(UiState.LOADING)
//        authRepository.generateOtp(_phoneNumber.value!!).bindUiState(_uiState)
        startTimer()
        startListenUserConsent()
    }

    fun submitOtp() = launch {

    }

    fun back() {
        navigator.back()
    }

    private fun startTimer() {
        stopTimer()
        countDownTimer.start()
    }

    private fun stopTimer() {
        countDownTimer.cancel()
    }

    private fun startListenUserConsent() {
        smsRetrieverClient.startSmsUserConsent(null)
    }

    fun getOtpOrNull(message: String?, pattern: String): String? {
        if (message == null) return null
        val otpPattern = Pattern.compile(pattern)
        val matcher = otpPattern.matcher(message)
        return if (matcher.find()) {
            matcher.group(0)
        } else {
            null
        }
    }
}