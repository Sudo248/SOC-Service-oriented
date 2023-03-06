//package com.sudo248.authservice.contronller.dto;
//
//import com.sudo248.authservice.repository.entity.Provider;
//import com.sudo248.authservice.service.dto.AccountModel;
//import lombok.Data;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Data
//public class UserDto {
//    private String userId;
//
//    private String phoneNumber;
//
//    private Provider provider;
//
//    private LocalDateTime createdAt;
//
//    private String fullName;
//
//    private LocalDate birthday;
//
//    private String bio;
//
//    private String imageUrl;
//
//    public UserDto() {
//    }
//
//    public UserDto(String userId) {
//        this.userId = userId;
//    }
//
//    public UserDto(String userId, String phoneNumber, Provider provider, String fullName, LocalDate birthday, String bio, String imageUrl, LocalDateTime createdAt) {
//        this.userId = userId;
//        this.phoneNumber = phoneNumber;
//        this.provider = provider;
//        this.createdAt = createdAt;
//        this.fullName = fullName;
//        this.birthday = birthday;
//        this.bio = bio;
//        this.imageUrl = imageUrl;
//    }
//
//    public AccountModel toUserModel() {
//        return new AccountModel(
//                userId,
//                phoneNumber,
//                provider,
//                fullName,
//                birthday,
//                bio,
//                imageUrl,
//                createdAt
//        );
//    }
//}
