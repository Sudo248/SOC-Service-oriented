package com.sudo248.soc.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel>() {
    override val layoutId: Int = R.layout.activity_auth
    override val viewModel: AuthViewModel by viewModels()
}