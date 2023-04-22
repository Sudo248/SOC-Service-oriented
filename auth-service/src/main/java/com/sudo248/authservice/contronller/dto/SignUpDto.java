package com.sudo248.authservice.contronller.dto;

import com.sudo248.authservice.repository.entity.Provider;
import com.sudo248.authservice.repository.entity.Role;
import com.sudo248.authservice.service.model.AccountModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String phoneNumber;

    private String password;

    private Provider provider;

    private Role role;
    public AccountModel toUserModel() {
        if (provider == null) {
            provider = Provider.AUTH_SERVICE;
        }
        if (role == null) {
            role = Role.CONSUMER;
        }
        AccountModel accountModel = new AccountModel();
        accountModel.setPhoneNumber(phoneNumber);
        accountModel.setPassword(password);
        accountModel.setProvider(provider);
        accountModel.setRole(role);
        accountModel.setCreatedAt(LocalDateTime.now());
        accountModel.setValidated(false);
        return accountModel;
    }
}
