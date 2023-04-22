package com.sudo248.soc.ui.activity.main.fragment.user

import java.util.*

interface ViewController {
    fun showDialogDatePicker(now: Date? = null)
    fun showBottomSheetChooseAddress()
    fun toast(message: String)
}