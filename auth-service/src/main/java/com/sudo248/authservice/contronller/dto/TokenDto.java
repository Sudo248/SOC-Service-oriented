package com.sudo248.authservice.contronller.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String userId;
    private final String token;
    private final String refreshToken;

    public TokenDto(String userId, String token, String refreshToken) {
        this.userId = userId;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public TokenDto(String token) {
        this.userId = userId;
        this.token = token;
        this.refreshToken = null;
    }

    public TokenDto(String userId, String token) {
        this.userId = userId;
        this.token = token;
        this.refreshToken = null;
    }
}
