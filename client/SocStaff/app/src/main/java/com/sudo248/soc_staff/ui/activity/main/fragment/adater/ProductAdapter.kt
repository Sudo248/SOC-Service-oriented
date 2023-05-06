package com.sudo248.soc_staff.ui.activity.main.fragment.adater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.soc_staff.databinding.ItemBinding
import com.sudo248.soc_staff.domain.entity.discovery.SupplierProductInfo
import com.sudo248.soc_staff.ui.uimodel.adapter.loadImage

class ProductAdapter (
    private val onItemClick: (SupplierProductInfo) -> Unit
) : BaseListAdapter<SupplierProductInfo, ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ViewHolder(binding: ItemBinding) : BaseViewHolder<SupplierProductInfo, ItemBinding>(binding) {
        override fun onBind(item: SupplierProductInfo) {
            binding.apply {
                loadImage(itemImg, item.productImages[0])
                idItem.text = item.productId
                nameItem.text = item.productName
                root.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }
    }
}