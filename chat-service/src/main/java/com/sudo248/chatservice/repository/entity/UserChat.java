package com.sudo248.chatservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChat {
    private String userId;
    private String fullName;
    private String image;
}
