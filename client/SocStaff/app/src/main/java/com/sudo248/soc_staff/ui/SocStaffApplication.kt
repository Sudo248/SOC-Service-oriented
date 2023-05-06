package com.sudo248.soc_staff.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import com.google.firebase.messaging.FirebaseMessaging
import com.sudo248.base_android.app.BaseApplication
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.domain.common.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SocStaffApplication : BaseApplication() {
    override val enableNetworkObserver: Boolean = true
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceUtils.createApplicationSharePreference(this)
        setupNotification()
        FirebaseMessaging.getInstance().subscribeToTopic("public.conversation")
    }

    private fun setupNotification() {

        val important = NotificationManager.IMPORTANCE_DEFAULT

        val messageChannel = NotificationChannel(
            Constants.Notification.MESSAGE_NOTIFICATION_CHANNEL_ID,
            Constants.Notification.MESSAGE_NOTIFICATION_CHANNEL_NAME,
            important
        )
        messageChannel.description = "This notification alert when new message"
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(messageChannel)
    }
}