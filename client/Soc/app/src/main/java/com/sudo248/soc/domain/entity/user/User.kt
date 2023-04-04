package com.sudo248.soc.domain.entity.user

data class User(
    val userId: String,
    val avatarUrl: String,
    val role: String,
    val name: String,
    val userName: String,
    val gender: String,
    val phoneNumber: String,
    val address: String,
    val bank: String,
) {
    companion object {
        fun empty(): User {
            return User(
                userId = "",
                avatarUrl = "",
                role = "",
                name = "",
                userName = "",
                gender = "",
                phoneNumber = "",
                address = "",
                bank = "",
            )
        }
    }
}
