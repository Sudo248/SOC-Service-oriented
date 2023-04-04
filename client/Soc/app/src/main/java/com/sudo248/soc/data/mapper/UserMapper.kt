package com.sudo248.soc.data.mapper

import com.sudo248.soc.data.dto.user.UserDto
import com.sudo248.soc.domain.entity.user.User

fun UserDto.toUser(): User {
    return User(
        userId = userId,
        avatarUrl = "",
        role = "",
        name = "",
        userName = "",
        gender = "",
        phoneNumber = "",
        address = "",
        bank = ""
    )
}