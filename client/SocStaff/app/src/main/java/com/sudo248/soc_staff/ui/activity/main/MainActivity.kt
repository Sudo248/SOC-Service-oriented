package com.sudo248.soc_staff.ui.activity.main;

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

import com.sudo248.base_android.base.BaseActivity;
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc_staff.databinding.ActivityMainBinding;
import com.sudo248.soc_staff.ui.ktx.showErrorDialog
import com.sudo248.soc_staff.ui.util.FileUtils

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.MediaType.Companion.toMediaTypeOrNull

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), PickImageController {
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

    override fun initView() {
        viewModel.pickImageController = this
    }

    override fun pickImage() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}