package com.sudo248.soc.ui.activity.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private val listFragmentHideBottomNav = listOf(
        R.id.productDetailFragment
    )

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id in listFragmentHideBottomNav) {
            goneBottomNav()
        } else {
            showBottomNav()
        }
    }

    private lateinit var navController: NavController

    override fun initView() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    private fun showBottomNav() {
        if (binding.bottomNav.isGone) {
            binding.bottomNav.visible()
        }
    }

    private fun goneBottomNav() {
        if (binding.bottomNav.isVisible) {
            binding.bottomNav.gone()
        }
    }
}