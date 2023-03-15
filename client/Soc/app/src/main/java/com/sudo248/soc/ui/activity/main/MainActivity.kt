package com.sudo248.soc.ui.activity.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun initView() {

    }
}