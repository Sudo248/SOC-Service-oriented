package com.sudo248.soc_staff.ui.activity.main.fragment.adater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.soc_staff.databinding.ItemPromotionBinding
import com.sudo248.soc_staff.domain.entity.promotion.Promotion
import com.sudo248.soc_staff.ui.util.Utils

class PromotionAdapter(
    private val onItemClick: (Promotion) -> Unit,
    private val onItemDelete: (Promotion) -> Unit,
) : BaseListAdapter<Promotion, PromotionAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemPromotionBinding) : BaseViewHolder<Promotion, ItemPromotionBinding>(binding) {
        override fun onBind(item: Promotion) {
            binding.apply {
                namePromotion.text = item.name
                price.text = Utils.formatVnCurrency(item.value)
                idPromotion.text = item.promotionId


            }

            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPromotionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}