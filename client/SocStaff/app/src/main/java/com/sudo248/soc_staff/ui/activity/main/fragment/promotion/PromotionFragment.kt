package com.sudo248.soc_staff.ui.activity.main.fragment.promotion

import android.app.Dialog
import android.content.res.ColorStateList
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.showWithTransparentBackground
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.databinding.DialogNotificationBinding
import com.sudo248.soc_staff.databinding.FragmentPromotionBinding
import com.sudo248.soc_staff.domain.entity.notification.Notification
import com.sudo248.soc_staff.domain.entity.promotion.Promotion
import com.sudo248.soc_staff.domain.ktx.format
import com.sudo248.soc_staff.ui.activity.main.fragment.adater.PromotionAdapter
import com.sudo248.soc_staff.ui.ktx.showErrorDialog
import com.sudo248.soc_staff.ui.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import okio.Utf8

@AndroidEntryPoint
class PromotionFragment : BaseFragment<FragmentPromotionBinding, PromotionViewModel>() {
    override val viewModel: PromotionViewModel by viewModels()

    override val enableStateScreen: Boolean
        get() = true

    private val adapter: PromotionAdapter by lazy {
        PromotionAdapter(
            onItemClick = {
                setPromotion(promotion = it)
                binding.apply {
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
            },
            onItemDelete = {
                DialogUtils.showConfirmDialog(
                    requireContext(),
                    title = "Delete promotion",
                    textColorTitle = R.color.primaryColor,
                    description = "Do you want to delete ${it.name} promotion?",
                    textColorDescription = R.color.black,
                    positive = "Yes",
                    negative = "No",
                    onPositive = {
                        viewModel.deletePromotion(it.promotionId)
                    }
                )
            }
        )
    }

    override fun initView() {
        binding.reView.adapter = adapter
        binding.btUpdate.setOnClickListener {
            getPromotion()?.let {
                viewModel.putPromotion(it)
            }
        }
        binding.btAdd.setOnClickListener {
            getPromotion()?.let {
                viewModel.postPromotion(it)
            }
        }

        binding.imgBack.setOnClickListener {
            back()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.promotions.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setPromotion(promotion: Promotion) {
        binding.apply {
            idPromotion.setText(promotion.promotionId)
            namePromotion.setText(promotion.name)
            edtPrice.setText(promotion.value.format(2))
        }
    }

    private fun getPromotion(): Promotion? {
        val idPromotion = binding.idPromotion.text.toString()
        val name = binding.namePromotion.text.toString()
        try {
            val price = binding.edtPrice.text.toString().toDouble()
            return Promotion(
                idPromotion,
                value = price,
                name = name
            )
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Value must be a number", Toast.LENGTH_SHORT).show()
        }
        return null
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
            idPromotion.setText("")
            namePromotion.setText("")
            edtPrice.setText("")

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

        viewModel.titleNotification?.let {
            viewModel.titleNotification = null
            showDialogConfigNotification(it)
        }
    }

    private fun showDialogConfigNotification(defaultTitle: String? = null) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogNotificationBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)

        defaultTitle?.let {
            dialogBinding.edtTitle.editText?.setText(it)
        }

        dialogBinding.txtCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.txtSend.setOnClickListener {
            val title = dialogBinding.edtTitle.editText?.text.toString()
            val content = dialogBinding.edtContent.editText?.text.toString()
            val notification = Notification(
                title = title,
                body = content
            )
            viewModel.sendNotification(notification)
            dialog.dismiss()
        }

        dialog.showWithTransparentBackground()
    }
}