package com.sudo248.soc_staff.data.api.auth.request

import com.sudo248.soc_staff.domain.entity.auth.Provider
import com.sudo248.soc_staff.domain.entity.auth.Role


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:21 - 04/03/2023
 */
data class AccountRequest(
    val phoneNumber: String,
    val password: String,
    val provider: Provider? = null,
    val role: Role = Role.STAFF
)