package com.sudo248.soc.ui.activity.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ActivityMainBinding
import com.sudo248.soc.ui.ktx.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), PickImageController {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.setImageUri(uri)
            } else {
                DialogUtils.showErrorDialog(
                    this,
                    "Pick image error"
                )
            }
        }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            viewModel.getCurrentLocation()
        }
    }

    private val listFragmentHideBottomNav = listOf(
        R.id.productDetailFragment,
        R.id.searchFragment
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
        requestPermission()
        viewModel.pickImageController = this
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

    override fun pickImage() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
}