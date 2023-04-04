package com.sudo248.soc.ui.uimodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

data class UserUiModel(
    val userId: String = "",
    val avatarUrl: ObservableField<String> = ObservableField(""),
    val role: ObservableField<String> = ObservableField(""),
    val name: ObservableField<String> = ObservableField(""),
    val userName: ObservableField<String> = ObservableField(""),
    val gender: ObservableField<String> = ObservableField(""),
    val phoneNumber: ObservableField<String> = ObservableField(""),
    val address: ObservableField<String> = ObservableField(""),
    val bank: ObservableField<String> = ObservableField(""),
)