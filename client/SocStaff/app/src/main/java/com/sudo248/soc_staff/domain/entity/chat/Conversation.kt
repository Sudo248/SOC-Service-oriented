package com.sudo248.soc_staff.domain.entity.chat

import com.sudo248.soc_staff.domain.entity.chat.Chat
import java.util.Date

data class Conversation(
    val topic: String,
    val conversationName: String,
    val conversationImage: String,
    val firstUserId: String,
    val secondUserId: String,
    val chats: List<Chat> = listOf(),
    val updateTime: Long
)