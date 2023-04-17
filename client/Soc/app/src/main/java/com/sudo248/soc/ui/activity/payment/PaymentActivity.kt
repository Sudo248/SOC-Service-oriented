package com.sudo248.soc.ui.activity.payment

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.base_android.ktx.showWithTransparentBackground
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ActivityPaymentBinding
import com.sudo248.soc.databinding.DialogChoosePaymentMethodBinding
import com.sudo248.soc.domain.common.Constants
import com.sudo248.soc.ui.ktx.showErrorDialog
import com.vnpay.authentication.VNP_AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.annotation.meta.When

@AndroidEntryPoint
class PaymentActivity : BaseActivity<ActivityPaymentBinding, PaymentViewModel>(), ViewController {
    override val viewModel: PaymentViewModel by viewModels()
    override val enableStateScreen: Boolean = true

    override fun initView() {
        binding.viewModel = viewModel
        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        binding.apply {
            lnPaymentMethod.setOnClickListener {
                showDialogChoosePaymentType()
            }
        }
    }

    override fun observer() {

    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showErrorDialog(
                    context = this@PaymentActivity,
                    message = message
                )
            }
        }
    }

    private fun showDialogChoosePaymentType() {
        val dialog = Dialog(this)
        val dialogBinding: DialogChoosePaymentMethodBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.dialog_choose_payment_method,
            binding.root as ViewGroup,
            false
        )
        dialog.setContentView(dialogBinding.root)
        dialogBinding.lifecycleOwner = this
        dialogBinding.viewModel = viewModel
        dialogBinding.txtAgree.setOnClickListener {
            dialog.hide()
        }
        dialog.setCancelable(false)
        dialog.showWithTransparentBackground()
    }

    override fun openVnPaySdk() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val vnPayUrl = viewModel.getVnPayPaymentUrl().await()
                vnPayUrl?.let {
                    val intentVnPaySdk = getIntentVnPay(it)
                    setupSdkCompletedCallback()
                    startActivity(intentVnPaySdk)
                }
            }
        }
    }

    private fun getIntentVnPay(vnPayUrl: String): Intent {
        return Intent(this, VNP_AuthenticationActivity::class.java).apply {
            putExtra(Constants.Payment.KEY_URL, vnPayUrl)
            putExtra(Constants.Payment.KEY_TMN_CODE, Constants.Payment.TMN_CODE)
            putExtra(Constants.Payment.KEY_SCHEME, Constants.Payment.SCHEME)
            putExtra(Constants.Payment.KEY_IS_SANDBOX, Constants.Payment.IS_SANDBOX)
        }
    }

    private fun setupSdkCompletedCallback() {
        VNP_AuthenticationActivity.setSdkCompletedCallback {
            when(it) {
                Constants.Payment.ACTION_APP_BACK -> {
                    onVnPaySdkActionAppBack()
                }
                Constants.Payment.ACTION_CALL_MOBILE_BANKING_APP -> {
                    onVnPaySdkActionCallMobileBankingApp()
                }
                Constants.Payment.ACTION_WEB_BACK -> {
                    onVnPaySdkActionWebBack()
                }
                Constants.Payment.ACTION_FAILED -> {
                    onVnPaySdkActionFailed()
                }
                Constants.Payment.ACTION_SUCCESS -> {
                    onVnPaySdkActionSuccess()
                }
                else -> {
                    onVnPaySdkActionFailed()
                }
            }
        }
    }

    private fun onVnPaySdkActionAppBack() {

    }

    private fun onVnPaySdkActionCallMobileBankingApp() {

    }

    private fun onVnPaySdkActionWebBack() {

    }

    private fun onVnPaySdkActionFailed() {
//        finish()
    }

    private fun onVnPaySdkActionSuccess() {
        finish()
    }
}