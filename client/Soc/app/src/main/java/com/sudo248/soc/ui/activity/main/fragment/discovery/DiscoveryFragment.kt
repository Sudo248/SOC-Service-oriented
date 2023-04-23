package com.sudo248.soc.ui.activity.main.fragment.discovery

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc.databinding.FragmentDiscoveryBinding
import com.sudo248.soc.ui.ktx.setHorizontalViewPort
import com.sudo248.soc.ui.ktx.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:34 - 12/03/2023
 */
@AndroidEntryPoint
class DiscoveryFragment : BaseFragment<FragmentDiscoveryBinding, DiscoveryViewModel>() {
    override val viewModel: DiscoveryViewModel by viewModels()
    override val enableStateScreen: Boolean = true

    override fun initView() {
        binding.viewModel = viewModel

        binding.rcvCategories.setHasFixedSize(true)
//        binding.rcvCategories.setHorizontalViewPort(3.5f)
        binding.rcvCategories.adapter = viewModel.categoryAdapter

        binding.rcvProducts.setHasFixedSize(true)
        binding.rcvProducts.adapter = viewModel.productAdapter

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