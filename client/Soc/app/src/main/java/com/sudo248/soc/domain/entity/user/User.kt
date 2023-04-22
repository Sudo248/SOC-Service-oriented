package com.sudo248.soc.domain.entity.user

import com.sudo248.soc.domain.entity.auth.Role
import java.util.Date

data class User(
    val userId: String = "",
    val avatar: String = "",
    val cover: String = "",
    val fullName: String = "",
    val gender: Gender = Gender.OTHER,
    val phone: String = "",
    val address: Address = Address(),
    val dob: Date = Date(),
    val role: Role = Role.CONSUMER
)
