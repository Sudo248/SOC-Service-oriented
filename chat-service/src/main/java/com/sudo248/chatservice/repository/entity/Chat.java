package com.sudo248.chatservice.repository.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat implements Serializable {
    private UserChat sender;

    private UserChat receiver;

    private String content;

    private long timestamp;

}
