package com.sudo248.authservice.contronller.dto;

import com.sudo248.authservice.repository.entity.Provider;
import com.sudo248.authservice.service.model.AccountModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignUpDto {
    private String phoneNumber;

    private String password;

    private Provider provider;


    public SignUpDto() {
    }

    public SignUpDto(String phoneNumber, String password, Provider provider) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.provider = provider;
    }

    public AccountModel toUserModel() {
        if (provider == null) {
            provider = Provider.AUTH_SERVICE;
        }
        return new AccountModel(
                phoneNumber,
                password,
                provider
        );
    }
}
