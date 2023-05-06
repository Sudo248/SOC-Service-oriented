package com.sudo248.chatservice.controller;

import com.sudo248.chatservice.controller.dto.ChatDto;
import com.sudo248.chatservice.repository.entity.User;
import com.sudo248.chatservice.service.ChatService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/conversation/info")
    ResponseEntity<BaseResponse<?>> getAllConversationInfo(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        return Utils.handleException(() -> BaseResponse.ok(chatService.getAllConversationInfo(userId)));
    }

    @GetMapping("/conversation/{topic}")
    ResponseEntity<BaseResponse<?>> getConversation(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("topic") String topic
    ) {
        return Utils.handleException(() -> BaseResponse.ok(chatService.getConversationByTopic(userId, getFullTopic(topic, userId))));
    }

    @PostMapping("/conversation/{topic}/send")
    ResponseEntity<BaseResponse<?>> postMessage(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @PathVariable("topic") String topic,
            @RequestBody ChatDto chat
    ) {
        return Utils.handleException(() -> BaseResponse.ok(chatService.sendMessageToConversation(userId, getFullTopic(topic, userId), chat)));
    }

    @PostMapping("/user")
    ResponseEntity<BaseResponse<?>> upsertUser(
            @RequestBody User user
    ) {
        return Utils.handleException(() -> BaseResponse.ok(chatService.upsertUser(user)));
    }

    private String getFullTopic(String topic, String userId) {
        if (topic.split("%").length == 3) {
            return topic;
        } else {
            return topic + "%" + userId;
        }
    }

}
