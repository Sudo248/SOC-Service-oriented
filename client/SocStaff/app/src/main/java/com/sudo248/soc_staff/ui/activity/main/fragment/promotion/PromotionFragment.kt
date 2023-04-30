package com.sudo248.soc_staff.ui.activity.main.fragment.promotion

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc_staff.databinding.FragmentPromotionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromotionFragment : BaseFragment<FragmentPromotionBinding, PromotionViewModel>() {
    override val viewModel: PromotionViewModel by viewModels()

    override fun initView() {

    }
}