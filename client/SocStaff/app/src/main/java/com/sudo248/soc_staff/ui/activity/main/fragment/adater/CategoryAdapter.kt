package com.sudo248.soc_staff.ui.activity.main.fragment.adater

import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.soc_staff.databinding.ItemBinding
import com.sudo248.soc_staff.domain.entity.discovery.CategoryInfo

class CategoryAdapter : BaseListAdapter<CategoryInfo, CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemBinding) : BaseViewHolder<CategoryInfo, ItemBinding>(binding) {
        override fun onBind(item: CategoryInfo) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }
}