package com.sudo248.soc_staff.domain.entity.auth


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:24 - 04/03/2023
 */
data class Token(
    val token: String,
    val refreshToken: String?,
)