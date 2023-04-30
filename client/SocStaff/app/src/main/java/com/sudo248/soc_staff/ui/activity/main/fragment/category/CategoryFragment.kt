package com.sudo248.soc_staff.ui.activity.main.fragment.category

import android.content.res.ColorStateList
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.databinding.FragmentCategoryBinding
import com.sudo248.soc_staff.domain.entity.discovery.Category
import com.sudo248.soc_staff.ui.activity.main.MainViewModel
import com.sudo248.soc_staff.ui.activity.main.fragment.adater.CategoryAdapter
import com.sudo248.soc_staff.ui.ktx.showErrorDialog
import com.sudo248.soc_staff.ui.uimodel.adapter.loadImage
import com.sudo248.soc_staff.ui.util.FileUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
    override val viewModel: CategoryViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override val enableStateScreen: Boolean
        get() = true

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter{
            binding.apply {
                idCategory.setText(it.categoryId)
                nameCategory.setText(it.name)
                imgCategory.visible()
                lnAddImage.gone()
                loadImage(imgCategory, it.image)
                btUpdate.apply {
                    isEnabled = true
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primaryColor))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }

                btAdd.apply {
                    isEnabled = false
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.button_disable))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }

            }
        }
    }

    override fun initView() {
        binding.apply {
            reView.adapter = categoryAdapter
            btAdd.setOnClickListener {
                viewModel.postCategory(getCategory())
            }
            lnAddImage.setOnClickListener {
                mainViewModel.pickImage()
            }
            imgBack.setOnClickListener {
                back()
            }
        }
    }

    override fun observer() {
        super.observer()
        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it.reversed())
        }
        mainViewModel.imageUri.observe(viewLifecycleOwner) {
            it?.let {
                loadImage(binding.imgCategory, it)
                binding.imgCategory.visible()
                binding.lnAddImage.gone()
            }
        }
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

    private fun getCategory(): Category {
        return Category(
            categoryId = binding.idCategory.text.toString(),
            name = binding.nameCategory.text.toString(),
            imageUrl = if (mainViewModel.imageUri.value != null) FileUtils.getPathFromUri(requireContext(), mainViewModel.imageUri.value!!) else "category_default.png",
            products = listOf()
        )
    }

    override fun onStateSuccess() {
        super.onStateSuccess()
        binding.apply {
            idCategory.setText("")
            nameCategory.setText("")
            lnAddImage.visible()
            imgCategory.gone()
            mainViewModel.setImageUri(null)
            btUpdate.apply {
                isEnabled = false
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.button_disable))
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }

            btAdd.apply {
                isEnabled = true
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primaryColor))
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
    }

}