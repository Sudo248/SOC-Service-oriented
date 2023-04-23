package com.sudo248.soc.data.dto.user

import com.sudo248.soc.domain.entity.user.Location

data class AddressDto(
    val provinceID: Int,
    val districtID: Int,
    val wardCode: String,
    val provinceName: String,
    val districtName: String,
    val wardName: String,
    val address: String,
    val location: Location,
    val fullAddress: String,
)