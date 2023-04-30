package com.sudo248.soc_staff.ui.activity.auth

import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.databinding.ActivityAuthBinding
import com.sudo248.soc_staff.ui.activity.auth.adapter.PageAuthAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {
    override val layoutId: Int = R.layout.activity_auth
    override val viewModel: AuthViewModel by viewModels()
    override val enableStateScreen: Boolean = true

    override fun initView() {
        binding.vpAuth.adapter = PageAuthAdapter(this)
        TabLayoutMediator(binding.tabAuth, binding.vpAuth) { tab, position ->
            tab.text = when(position){
                0 -> getString(R.string.sign_in)
                1 -> getString(R.string.sign_up)
                else -> throw IndexOutOfBoundsException("Tab has size 2")
            }
        }.attach()
    }

    fun requestViewPagerLayout() {
        binding.vpAuth.requestLayout()
    }
}