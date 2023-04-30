package com.sudo248.soc_staff.data.dto.user

import com.sudo248.soc_staff.domain.entity.auth.Role
import com.sudo248.soc_staff.domain.entity.user.Gender
import java.util.*

data class UserDto(
    val userId: String = "",
    val fullName: String = "",
    val phone: String = "",
    val dob: Date = Date(),
    val bio: String = "",
    val avatar: String,
    val cover: String = "",
    val address: AddressDto = AddressDto(),
    val gender: Gender = Gender.OTHER,
    val role: Role = Role.STAFF
)
