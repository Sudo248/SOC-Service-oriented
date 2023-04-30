package com.sudo248.soc_staff.ui.activity.main.fragment.user

import androidx.fragment.app.activityViewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc_staff.databinding.FragmentUserBinding
import com.sudo248.soc_staff.ui.activity.main.MainViewModel
import com.sudo248.soc_staff.ui.ktx.showErrorDialog
import com.sudo248.soc_staff.ui.uimodel.adapter.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>() {
    override val viewModel: UserViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    override val enableStateScreen: Boolean = true

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.setActivityViewModel(mainViewModel)
    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showErrorDialog(
                    context = requireContext(),
                    message = message
                )
            }
        }
    }
}