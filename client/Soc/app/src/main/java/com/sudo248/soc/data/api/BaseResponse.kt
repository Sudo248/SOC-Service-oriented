package com.sudo248.soc.data.api


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:11 - 04/03/2023
 */
open class BaseResponse<Data> {
    var statusCode: Int = 0
    var success: Boolean = false
    var message: String = ""
    var data: Data? = null
}