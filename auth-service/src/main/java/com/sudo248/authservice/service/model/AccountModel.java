package com.sudo248.authservice.service.model;

import com.sudo248.authservice.repository.entity.Provider;
import com.sudo248.authservice.repository.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    private String userId;

    private String phoneNumber;

    // hash by bcrypt
    private String password;

    private Provider provider;

    private boolean isValidated;

    private LocalDateTime createdAt;

    private Role role;
}
