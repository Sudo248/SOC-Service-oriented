package com.sudo248.soc_staff.data.mapper

import com.sudo248.soc.data.dto.auth.TokenDto
import com.sudo248.soc.domain.entity.auth.Token

fun TokenDto.toToken(): Token {
    return Token(
        token = this.token,
        refreshToken = this.refreshToken
    )
}