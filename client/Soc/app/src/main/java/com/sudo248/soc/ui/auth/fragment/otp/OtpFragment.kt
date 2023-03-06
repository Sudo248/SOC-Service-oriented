package com.sudo248.soc.ui.auth.fragment.otp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc.R
import com.sudo248.soc.databinding.FragmentOtpBinding

class OtpFragment : BaseFragment<FragmentOtpBinding, OtpViewModel>() {
    override val layoutId: Int = R.layout.fragment_otp
    override val viewModel: OtpViewModel by viewModels()

}