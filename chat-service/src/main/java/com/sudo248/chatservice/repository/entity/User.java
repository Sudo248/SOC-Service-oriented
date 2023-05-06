package com.sudo248.chatservice.repository.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @DocumentId
    private String userId;
    private String fullName;
    private String image;
    private String token;

    public UserChat toUserChat() {
        return new UserChat(
          userId,
          fullName,
          image
        );
    }
}
