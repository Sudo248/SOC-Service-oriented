package com.sudo248.soc_staff.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc_staff.data.api.chat.ChatService
import com.sudo248.soc_staff.data.dto.chat.ChatDto
import com.sudo248.soc_staff.data.ktx.data
import com.sudo248.soc_staff.data.ktx.errorBody
import com.sudo248.soc_staff.domain.entity.chat.Chat
import com.sudo248.soc_staff.domain.entity.chat.Conversation
import com.sudo248.soc_staff.domain.entity.chat.ConversationInfo
import com.sudo248.soc_staff.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService,
    private val ioDispatcher: CoroutineDispatcher,
) : ChatRepository {
    override suspend fun getConversation(topic: String): DataState<Conversation, Exception> =
        stateOn(ioDispatcher) {
            val response = handleResponse(
                chatService.getConversation(
                    topic
                )
            )
            if (response.isSuccess) {
                response.data()
            } else {
                throw response.error().errorBody()
            }
        }

    override suspend fun getAllConversationInfo(): DataState<List<ConversationInfo>, Exception> =
        stateOn(ioDispatcher) {
            val response = handleResponse(
                chatService.getAllConversationInfo()
            )
            if (response.isSuccess) {
                response.data()
            } else {
                throw response.error().errorBody()
            }
        }
    override suspend fun sendMessageToTopic(
        topic: String,
        content: String
    ): DataState<Chat, Exception> = stateOn(ioDispatcher) {
        val response = handleResponse(
            chatService.sendMessageToTopic(
                topic,
                ChatDto(
                    topic,
                    content
                )
            )
        )
        if (response.isSuccess) {
            response.data()
        } else {
            throw response.error().errorBody()
        }
    }
}