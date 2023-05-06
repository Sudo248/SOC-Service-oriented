package com.sudo248.soc.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.facebook.FacebookSdk
import com.google.firebase.messaging.FirebaseMessaging
import com.sudo248.base_android.app.BaseApplication
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc.domain.common.Constants
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
        setupNotification()
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.Notification.PROMOTION_TOPIC)
    }

    private fun setupNotification() {
        val important = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            Constants.Notification.PROMOTION_NOTIFICATION_CHANNEL_ID,
            Constants.Notification.PROMOTION_NOTIFICATION_CHANNEL_NAME,
            important
        )
        channel.description = "This notification alert when merchant add new or update a promotion"
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}