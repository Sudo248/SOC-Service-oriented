package com.sudo248.chatservice.service;

import com.sudo248.chatservice.controller.dto.ChatDto;
import com.sudo248.chatservice.repository.entity.Chat;
import com.sudo248.chatservice.repository.entity.Conversation;
import com.sudo248.chatservice.repository.entity.ConversationInfo;
import com.sudo248.chatservice.repository.entity.User;

import java.util.List;

public interface ChatService {

    static final String conversationTopic = "public.conversation";

    List<ConversationInfo> getAllConversationInfo(String userId);

    Conversation getConversationByTopic(String userId, String topic);

    Chat sendMessageToConversation(String userId, String topic, ChatDto chatDto) throws Exception;

    User upsertUser(User user);

}