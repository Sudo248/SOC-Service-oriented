package com.sudo248.soc_staff.ui

import com.sudo248.base_android.app.BaseApplication
import com.sudo248.base_android.utils.SharedPreferenceUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SocStaffApplication : BaseApplication() {
    override val enableNetworkObserver: Boolean = true
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtils.createApplicationSharePreference(this)
    }
}