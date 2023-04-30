package com.sudo248.soc.ui.activity.main.fragment.product_detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.share.Sharer
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 15:52 - 20/03/2023
 */
@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>() {
    override val viewModel: ProductDetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()

    private val callbackManager = CallbackManager.Factory.create()

    override fun initView() {
        viewModel.product = args.product
        binding.viewModel = viewModel
        viewModel.getSupplierAddress()
        try {
            setupSendMessage()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setupSendMessage() {
        val content = ShareLinkContent.Builder().setContentUrl(Uri.parse("https://shp.ee/m8ijte6")).build()
        binding.btnSendMessage.shareContent = content
        binding.btnSendMessage.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result> {
            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {

            }

            override fun onSuccess(result: Sharer.Result) {

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}