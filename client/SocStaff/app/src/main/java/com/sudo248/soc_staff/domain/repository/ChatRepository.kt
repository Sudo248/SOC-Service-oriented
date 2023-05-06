package com.sudo248.soc_staff.domain.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.soc_staff.domain.entity.chat.Chat
import com.sudo248.soc_staff.domain.entity.chat.Conversation
import com.sudo248.soc_staff.domain.entity.chat.ConversationInfo

interface ChatRepository {
    suspend fun getConversation(topic: String): DataState<Conversation, Exception>
    suspend fun getAllConversationInfo(): DataState<List<ConversationInfo>, Exception>
    suspend fun sendMessageToTopic(topic: String, content: String): DataState<Chat, Exception>
}