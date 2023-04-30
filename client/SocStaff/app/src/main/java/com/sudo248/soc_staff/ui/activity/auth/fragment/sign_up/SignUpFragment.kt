package com.sudo248.soc_staff.ui.activity.auth.fragment.sign_up

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.databinding.FragmentSignUpBinding
import com.sudo248.soc_staff.ui.activity.auth.AuthActivity
import com.sudo248.soc_staff.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override val layoutId: Int = R.layout.fragment_sign_up
    override val viewModel: SignUpViewModel by viewModels()
    private val authViewModel: AuthViewModel by activityViewModels()

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.setParentViewModel(authViewModel)
        (activity as AuthActivity).requestViewPagerLayout()
    }
}