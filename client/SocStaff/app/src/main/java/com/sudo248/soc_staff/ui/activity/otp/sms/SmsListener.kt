package com.sudo248.soc.ui.activity.otp.sms

import android.content.Intent


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 22:30 - 13/03/2023
 */
interface SmsListener {
    fun onReceiveSms(smsIntent: Intent)
    fun onFailedToReceiveSms()
}