package com.sudo248.soc.ui.auth.fragment.sign_in

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.soc.R
import com.sudo248.soc.databinding.FragmentSignInBinding
import com.sudo248.soc.ui.auth.AuthActivity
import com.sudo248.soc.ui.main.MainActivity

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>() {
    override val layoutId: Int = R.layout.fragment_sign_in
    override val viewModel: SignInViewModel by viewModels()

    override fun onStateSuccess() {
        super.onStateSuccess()
        (requireActivity() as AuthActivity).navigateOff(MainActivity::class.createActionIntentDirections())
    }
}