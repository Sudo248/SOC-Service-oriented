package com.sudo248.soc.ui.mapper

import androidx.databinding.ObservableField
import com.sudo248.soc.domain.entity.user.User
import com.sudo248.soc.ui.uimodel.UserUiModel

fun User.toUserUi(): UserUiModel {
    return UserUiModel(
        userId = userId,
        avatarUrl = ObservableField(avatarUrl),
        role = ObservableField(role),
        name = ObservableField(name),
        userName = ObservableField(userName),
        gender = ObservableField(gender),
        phoneNumber = ObservableField(phoneNumber),
        address = ObservableField(address),
        bank = ObservableField(bank),
    )
}