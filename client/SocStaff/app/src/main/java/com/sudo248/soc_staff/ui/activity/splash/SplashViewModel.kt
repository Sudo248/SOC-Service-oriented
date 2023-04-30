package com.sudo248.soc_staff.ui.activity.splash

import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.repository.AuthRepository
import com.sudo248.soc_staff.ui.activity.auth.AuthActivity
import com.sudo248.soc_staff.ui.activity.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 08:12 - 06/03/2023
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AuthRepository
) : BaseViewModel<IntentDirections>() {
    init {
        launch {
//            SharedPreferenceUtils.putString(Constants.Key.SUPPLIER_ID, "c48aff66-5d77-4d63-94c7-706a617387b5-1682099031509")
            delay(1000)
            accountRepository.tryGetToken()
                .onSuccess {
                    navigator.navigateOff(MainActivity::class.createActionIntentDirections())
                }
                .onError {
                    navigator.navigateOff(AuthActivity::class.createActionIntentDirections())
                }
//            navigator.navigateOff(OtpActivity::class.createActionIntentDirections{
//                putExtra(Constants.Key.PHONE_NUMBER, "0989465270")
//            })
//            navigator.navigateOff(MainActivity::class.createActionIntentDirections())
//            navigator.navigateOff(PaymentActivity::class.createActionIntentDirections())
        }
    }
}