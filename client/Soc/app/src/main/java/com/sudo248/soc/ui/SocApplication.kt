package com.sudo248.soc.ui

import com.facebook.FacebookSdk
import com.sudo248.base_android.app.BaseApplication
import com.sudo248.base_android.utils.SharedPreferenceUtils
import dagger.hilt.android.HiltAndroidApp


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:50 - 05/03/2023
 */

@HiltAndroidApp
class SocApplication : BaseApplication() {
    override val enableNetworkObserver: Boolean = true
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtils.createApplicationSharePreference(this)
        FacebookSdk.setApplicationId("6161985857254099")
    }
}