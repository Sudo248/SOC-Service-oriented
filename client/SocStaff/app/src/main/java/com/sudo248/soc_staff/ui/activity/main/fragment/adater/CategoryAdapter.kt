package com.sudo248.soc_staff.ui.activity.main.fragment.adater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.base_android.ktx.gone
import com.sudo248.soc_staff.databinding.ItemBinding
import com.sudo248.soc_staff.domain.entity.discovery.CategoryInfo
import com.sudo248.soc_staff.ui.uimodel.adapter.loadImage

class CategoryAdapter(
    private val canDelete: Boolean = true,
    private val onItemClick: (CategoryInfo) -> Unit
) : BaseListAdapter<CategoryInfo, CategoryAdapter.ViewHolder>() {

    private val allData = mutableListOf<CategoryInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ViewHolder(binding: ItemBinding) : BaseViewHolder<CategoryInfo, ItemBinding>(binding) {
        override fun onBind(item: CategoryInfo) {
            binding.apply {
                loadImage(itemImg, item.image)
                idItem.text = item.categoryId
                nameItem.text = item.name
                if (!canDelete) imgDelete.gone()
                root.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }
    }

    override fun submitList(list: List<CategoryInfo>?) {
        super.submitList(list)
        allData.clear()
        allData.addAll(list ?: listOf())
    }

    fun search(name: String) {
        val searchName = name.lowercase()
        val searchList = allData.filter { it.name.lowercase().contains(searchName) }
        super.submitList(searchList)
    }
}