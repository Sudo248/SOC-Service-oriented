package com.sudo248.soc.data.ktx

import com.sudo248.base_android.exception.ApiException
import com.sudo248.soc.data.api.BaseResponse


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 07:45 - 06/03/2023
 */

fun ApiException.Companion.fromResponse(response: BaseResponse<*>): ApiException {
    return ApiException(
        statusCode = response.statusCode,
        message = response.message,
        data = response.data
    )
}