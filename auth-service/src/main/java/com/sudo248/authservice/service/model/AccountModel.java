package com.sudo248.authservice.service.model;

import com.sudo248.authservice.repository.entity.Provider;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountModel {
    private String userId;

    private String phoneNumber;

    // hash by bcrypt
    private String password;

    private Provider provider;

    private boolean isValidated;

    private LocalDateTime createdAt;

    public AccountModel() {
    }

    public AccountModel(String userId) {
        this.userId = userId;
        this.isValidated = true;
    }

    public AccountModel(String userId, String phoneNumber, String password, Provider provider, boolean isValidated, LocalDateTime createdAt) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.provider = provider;
        this.isValidated = isValidated;
        this.createdAt = createdAt;
    }

    public AccountModel(String phoneNumber, String password) {
        this(phoneNumber, password, Provider.AUTH_SERVICE);
    }

    public AccountModel(String phoneNumber, String password, Provider provider) {
        this(null, phoneNumber, password, provider);
    }

    public AccountModel(String userId, String phoneNumber, String password, Provider provider, LocalDateTime createdAt) {
        this(userId, phoneNumber, password, provider, false, createdAt);
    }

    public AccountModel(String userId, String phoneNumber, String password, Provider provider) {
        this(userId, phoneNumber, password, provider, LocalDateTime.now());
    }
}
