package com.sudo248.authservice.contronller.dto;

import com.sudo248.authservice.service.model.AccountModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {

    private String phoneNumber;

    private String password;
}
