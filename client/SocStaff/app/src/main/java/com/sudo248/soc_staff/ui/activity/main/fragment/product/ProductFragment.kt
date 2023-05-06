package com.sudo248.soc_staff.ui.activity.main.fragment.product

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.base_android.navigation.ResultCallback
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.databinding.FragmentProductBinding
import com.sudo248.soc_staff.domain.common.Constants
import com.sudo248.soc_staff.domain.entity.discovery.CategoryInfo
import com.sudo248.soc_staff.domain.entity.discovery.Product
import com.sudo248.soc_staff.domain.entity.discovery.Route
import com.sudo248.soc_staff.domain.entity.discovery.SupplierProduct
import com.sudo248.soc_staff.ui.activity.main.MainViewModel
import com.sudo248.soc_staff.ui.activity.main.fragment.adater.ProductAdapter
import com.sudo248.soc_staff.ui.ktx.showErrorDialog
import com.sudo248.soc_staff.ui.uimodel.adapter.loadImage
import com.sudo248.soc_staff.ui.util.FileUtils
import com.sudo248.soc_staff.ui.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {
    override val viewModel: ProductViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override val enableStateScreen: Boolean
        get() = true

    private val adapter: ProductAdapter by lazy {
        ProductAdapter {
            viewModel.currentProductInfo = it
            binding.apply {
                nameCategory.setText(it.categoryName)
                nameProduct.setText(it.productName)
                descProduct.setText(it.description)
                soLuongProduct.setText(it.amountLeft.toString())
                priceProduct.setText(it.price.toInt().toString())

                lnAddImage.gone()
                imgCategory.visible()
                loadImage(imgCategory, it.productImages[0])

                btUpdate.apply {
                    isEnabled = true
                    backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.primaryColor
                        )
                    )
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }

                btAdd.apply {
                    isEnabled = false
                    backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.button_disable
                        )
                    )
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
            }
        }
    }

    override fun initView() {
        binding.apply {
            reView.adapter = adapter
            nameCategory.setOnClickListener {
                navigateForResult(
                    ProductFragmentDirections.actionProductFragmentToSearchCategoryFragment(),
                    Constants.Key.CATEGORY,
                    object : ResultCallback {
                        override fun onResult(key: String, data: Bundle?) {
                            data?.let {
                                val categoryInfo =
                                    it.getSerializable(Constants.Key.CATEGORY) as CategoryInfo
                                viewModel.categoryInfo.postValue(categoryInfo)
                            }
                        }
                    })
            }

            imgBack.setOnClickListener {
                back()
            }

            btAdd.setOnClickListener {
                viewModel.postProduct(getProduct())
            }

            btUpdate.setOnClickListener {
                viewModel.putProduct(getProduct())
            }

            lnAddImage.setOnClickListener {
                mainViewModel.pickImage()
            }
        }
    }

    override fun observer() {
        super.observer()
        viewModel.categoryInfo.observe(viewLifecycleOwner) {
            it?.let {
                binding.nameCategory.setText(it.name)
            }
        }
        mainViewModel.imageUri.observe(viewLifecycleOwner) {
            it?.let {
                loadImage(binding.imgCategory, it)
                binding.imgCategory.visible()
                binding.lnAddImage.gone()
            }
        }
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it.reversed())
        }
    }

    private fun getProduct(): Product {
        return Product(
            productId = viewModel.currentProductInfo?.productId ?: "",
            name = binding.nameProduct.text.toString(),
            description = binding.descProduct.text.toString(),
            sku = viewModel.currentProductInfo?.sku ?: "",
            images = listOf(
                if (mainViewModel.imageUri.value != null) FileUtils.getPathFromUri(
                    requireContext(),
                    mainViewModel.imageUri.value!!
                ) else if (viewModel.currentProductInfo != null)
                    viewModel.currentProductInfo!!.productImages[0]
                else "product_default.png"
            ),
            categoryId = viewModel.categoryInfo.value?.categoryId
                ?: viewModel.currentProductInfo?.categoryId ?: "",
            supplierProducts = listOf(
                SupplierProduct(
                    supplierId = SharedPreferenceUtils.getString(Constants.Key.SUPPLIER_ID),
                    productId = viewModel.currentProductInfo?.productId ?: "",
                    route = Route(),
                    amountLeft = binding.soLuongProduct.text.toString().toInt(),
                    price = binding.priceProduct.text.toString().toDouble(),
                    soldAmount = 0,
                    rate = 0.0
                )
            )
        )
    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showErrorDialog(
                    context = requireContext(),
                    message = message
                )
            }
        }
    }

    override fun onStateSuccess() {
        super.onStateSuccess()
        binding.apply {
            nameCategory.setText("")
            nameProduct.setText("")
            descProduct.setText("")
            soLuongProduct.setText("")
            priceProduct.setText("")

            lnAddImage.visible()
            imgCategory.gone()
            mainViewModel.setImageUri(null)
            viewModel.currentProductInfo = null
            viewModel.categoryInfo.postValue(null)

            btUpdate.apply {
                isEnabled = false
                backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_disable
                    )
                )
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }

            btAdd.apply {
                isEnabled = true
                backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primaryColor
                    )
                )
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
    }
}