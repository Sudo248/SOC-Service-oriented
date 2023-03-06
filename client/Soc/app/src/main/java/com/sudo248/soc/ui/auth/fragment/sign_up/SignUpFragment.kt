package com.sudo248.soc.ui.auth.fragment.sign_up

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc.R
import com.sudo248.soc.databinding.FragmentSignUpBinding


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override val layoutId: Int = R.layout.fragment_sign_up
    override val viewModel: SignUpViewModel by viewModels()

}