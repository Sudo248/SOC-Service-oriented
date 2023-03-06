package com.sudo248.soc.ui.splash

import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.navigation.IntentDirections
import com.sudo248.soc.ui.auth.AuthActivity
import com.sudo248.soc.ui.main.MainActivity
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
            delay(2000)
            navigator.navigateOff(AuthActivity::class.createActionIntentDirections())
        }
    }
}