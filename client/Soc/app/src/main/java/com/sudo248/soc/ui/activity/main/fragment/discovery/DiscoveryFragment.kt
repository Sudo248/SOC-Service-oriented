package com.sudo248.soc.ui.activity.main.fragment.discovery

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc.databinding.FragmentDiscoveryBinding
import com.sudo248.soc.ui.ktx.setHorizontalViewPort
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
        binding.rcvCategories.setHorizontalViewPort(3.5f)
    }
}