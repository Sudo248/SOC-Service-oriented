package com.sudo248.soc_staff.data.ktx

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.exception.ApiException
import com.sudo248.soc_staff.data.api.BaseResponse

fun <Data> DataState<BaseResponse<Data>, ApiException>.data(): Data {
    if (isSuccess) {
        return get().data!!
    } else {
        throw error()
    }
}