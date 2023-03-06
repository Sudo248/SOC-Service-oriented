package com.sudo248.soc.ui.splash

import android.annotation.SuppressLint
import androidx.activity.viewModels
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutId: Int = R.layout.activity_splash
    override val viewModel: SplashViewModel by viewModels()
}