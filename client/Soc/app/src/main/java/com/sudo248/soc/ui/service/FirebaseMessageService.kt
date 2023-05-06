package com.sudo248.soc.ui.service

import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc.R
import com.sudo248.soc.domain.common.Constants

class FirebaseMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        SharedPreferenceUtils.putString(Constants.Key.FCM_TOKEN, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("Sudoo", "onMessageReceived: $message")
        message.notification?.let {
            pushNotification(
                Constants.Notification.PROMOTION_NOTIFICATION_CHANNEL_ID,
                it
            )
        }
    }

    private fun pushNotification(channelId: String, notification: RemoteMessage.Notification) {
        val _notification = NotificationCompat.Builder(
            applicationContext,
            channelId
        ).apply {
            setContentTitle(notification.title)
            setContentText(notification.body)
            setSmallIcon(R.drawable.ic_notifications_active)
            setDefaults(NotificationCompat.DEFAULT_SOUND)
            color = getColor(R.color.primaryColor)
            setCategory(NotificationCompat.CATEGORY_ALARM)
        }.build()
        (applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(
            Constants.Notification.PROMOTION_NOTIFICATION_ID,
            _notification
        )
    }
}