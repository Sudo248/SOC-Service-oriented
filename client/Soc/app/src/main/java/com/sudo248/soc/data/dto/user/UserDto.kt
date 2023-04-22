package com.sudo248.soc.data.dto.user

import com.sudo248.soc.domain.entity.auth.Role
import com.sudo248.soc.domain.entity.user.Gender
import java.util.Date

data class UserDto(
    val userId: String,
    val fullName: String,
    val phone: String,
    val dob: Date,
    val bio: String = "",
    val avatar: String,
    val cover: String = "",
    val address: AddressDto,
    val gender: Gender,
    val role: Role = Role.CONSUMER
)
