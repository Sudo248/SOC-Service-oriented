package com.sudo248.soc.ui.activity.splash

import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.soc.domain.common.Constants
import com.sudo248.soc.ui.activity.auth.AuthActivity
import com.sudo248.soc.ui.activity.main.MainActivity
import com.sudo248.soc.ui.activity.otp.OtpActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 08:12 - 06/03/2023
 */
class SplashViewModel : BaseViewModel<IntentDirections>() {
    init {
        launch {
            delay(1000)
//            navigator.navigateOff(AuthActivity::class.createActionIntentDirections())
//            navigator.navigateOff(OtpActivity::class.createActionIntentDirections{
//                putExtra(Constants.Key.PHONE_NUMBER, "0989465270")
//            })
            navigator.navigateOff(MainActivity::class.createActionIntentDirections())
        }
    }
}