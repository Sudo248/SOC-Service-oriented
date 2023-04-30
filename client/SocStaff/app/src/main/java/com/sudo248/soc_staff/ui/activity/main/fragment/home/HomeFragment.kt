package com.sudo248.soc_staff.ui.activity.main.fragment.home

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.databinding.FragmentHomeBinding
import com.sudo248.soc_staff.domain.common.Constants

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun initView() {
        binding.viewModel = viewModel
        if (SharedPreferenceUtils.getBoolean(Constants.Key.MUST_SETUP_LOCATION)) {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToUserEditFragment())
        }
    }
}