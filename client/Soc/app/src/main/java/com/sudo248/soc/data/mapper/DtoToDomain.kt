package com.sudo248.soc.data.mapper

import com.sudo248.soc.data.dto.TokenDto
import com.sudo248.soc.domain.entity.auth.Token


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:30 - 05/03/2023
 */

fun TokenDto.toToken(): Token {
    return Token(
        token = this.token,
        refreshToken = this.refreshToken
    )
}