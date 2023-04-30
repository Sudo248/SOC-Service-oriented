package com.sudo248.soc_staff.domain.common


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 00:08 - 12/03/2023
 */
object Constants {
    const val TIMEOUT_OTP = 30_000L
    const val PATTERN_OTP = "(|^)\\d{6}"
    const val UNKNOWN_ERROR = "Unknown error"

    object Key {
        const val PHONE_NUMBER = "PHONE_NUMBER"
        const val TOKEN = "TOKEN"
    }

    object Payment {
        const val KEY_URL = "url"
        const val KEY_TMN_CODE = "tmn_code"
        const val KEY_SCHEME = "scheme"
        const val KEY_IS_SANDBOX = "is_sandbox"

        const val TMN_CODE = "BQPK5W1P"
        const val SCHEME = "resultactivity"
        const val IS_SANDBOX = true

        // Người dùng nhấn back từ sdk để quay lại
        const val ACTION_APP_BACK = "AppBackAction"

        //Người dùng nhấn chọn thanh toán qua app thanh toán (Mobile Banking, Ví...)
        //lúc này app tích hợp sẽ cần lưu lại cái PNR, khi nào người dùng mở lại app tích hợp thì
        // sẽ gọi kiểm tra trạng thái thanh toán của PNR Đó xem đã thanh toán hay chưa.
        const val ACTION_CALL_MOBILE_BANKING_APP = "CallMobileBankingApp"

        //Người dùng nhấn back từ trang thanh toán thành công khi thanh toán qua thẻ khi url
        // có chứa: cancel.sdk.merchantbackapp
        const val ACTION_WEB_BACK = "WebBackAction"

        //giao dịch thanh toán bị failed
        const val ACTION_FAILED = "FaildBackAction"

        //thanh toán thành công trên webview
        const val ACTION_SUCCESS = "SuccessBackAction"
    }

    object Images{
        const val DEFAULT_USER_IMAGE = "user_default.png"
        const val DEFAULT_CATEGORY_IMAGE = "category_default.png"
        const val DEFAULT_PRODUCT_IMAGE = "product_default.png"
    }
}