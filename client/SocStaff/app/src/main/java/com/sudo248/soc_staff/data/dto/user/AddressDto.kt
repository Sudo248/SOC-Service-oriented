package com.sudo248.soc_staff.data.dto.user

import com.sudo248.soc_staff.domain.entity.user.Location

data class AddressDto(
    val addressId: String = "",
    val provinceID: Int = 0,
    val districtID: Int = 0,
    val wardCode: String = "",
    val provinceName: String = "",
    val districtName: String = "",
    val wardName: String = "",
    val address: String = "",
    val location: Location = Location(),
    val fullAddress: String = "",
)