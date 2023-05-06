package com.sudo248.soc_staff.ui.service

import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.entity.chat.Chat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

class FirebaseMessageService : FirebaseMessagingService() {

    companion object {
        val chatFlow = MutableSharedFlow<Chat>()
        val conversationFlow = MutableSharedFlow<Boolean>()
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val clientId = SharedPreferenceUtils.getString(Constants.Key.USER_ID)

    private val gson = Gson()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        SharedPreferenceUtils.putString(Constants.Key.FCM_TOKEN, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.data.containsKey("chat")) {
            try {
                val chat = gson.fromJson(message.data["chat"], Chat::class.java)
                Log.d("Sudoo", "onMessageReceived: $chat")
                if (chat.sender.userId != clientId) {
                    scope.launch {
                        chatFlow.emit(chat)
                    }
                    message.notification?.let {
                        pushNotification(
                            Constants.Notification.MESSAGE_NOTIFICATION_CHANNEL_ID,
                            Constants.Notification.MESSAGE_NOTIFICATION_ID,
                            it
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        message.notification?.let {
            scope.launch {
                conversationFlow.emit(true)
            }
        }
    }

    private fun pushNotification(
        channelId: String,
        notificationId: Int,
        notification: RemoteMessage.Notification
    ) {
        val _notification = NotificationCompat.Builder(
            applicationContext,
            channelId
        ).apply {
            setContentTitle(notification.title)
            setContentText(notification.body)
            setSmallIcon(R.mipmap.ic_launcher_round)
            setDefaults(NotificationCompat.DEFAULT_SOUND)
            color = getColor(R.color.primaryColor)
            setCategory(NotificationCompat.CATEGORY_ALARM)
        }.build()
        (applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(
            notificationId,
            _notification
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}