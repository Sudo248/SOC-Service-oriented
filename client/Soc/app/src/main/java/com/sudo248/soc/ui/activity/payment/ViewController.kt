package com.sudo248.soc.ui.activity.payment

interface ViewController {
    fun openVnPaySdk()
    fun payWithCODSuccess()
    fun toast(message: String)
}