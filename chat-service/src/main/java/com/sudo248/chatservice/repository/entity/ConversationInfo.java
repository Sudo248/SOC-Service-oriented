package com.sudo248.chatservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationInfo {
    private String topic;
    private String conversationName;
    private String conversationImage;
    private String latestMessage;
    private long updateTime;
}
