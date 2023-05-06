package com.sudo248.soc.domain.entity.chat

data class ConversationInfo(
    val topic: String,
    val conversationName: String,
    val latestMessage: String
)