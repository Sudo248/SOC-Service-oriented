package com.sudo248.soc_staff.ui.uimodel

import androidx.databinding.ObservableField
import com.sudo248.soc_staff.domain.entity.auth.Role
import com.sudo248.soc_staff.domain.entity.user.Gender

data class UserUiModel(
    var userId: String = "",
    val avatar: ObservableField<String> = ObservableField(""),
    val cover: ObservableField<String> = ObservableField(""),
    val fullName: ObservableField<String> = ObservableField(""),
    val gender: ObservableField<String> = ObservableField(Gender.OTHER.value),
    val phone: ObservableField<String> = ObservableField(""),
    val address: AddressUiModel = AddressUiModel(),
    val dob: ObservableField<String> = ObservableField(""),
    val role: ObservableField<String> = ObservableField(Role.STAFF.value)
)