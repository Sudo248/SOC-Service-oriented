package com.sudo248.authservice.contronller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;

    private String fullName = "Soc-User";

    private String phone;

    private Date dob = new Date();

    private String bio = "";

    private String avatar = "user_default.png";

    private String cover = "user_default.png";

    private AddressDto address;
}
