package com.sudo248.userservice.service;

import com.sudo248.userservice.controller.dto.UserDto;
import com.sudo248.userservice.repository.entitity.User;

public interface UserService {
    UserDto getUser(String userId);
    UserDto postUser(UserDto userDto);
    UserDto putUser(String userId, UserDto userDto);
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
