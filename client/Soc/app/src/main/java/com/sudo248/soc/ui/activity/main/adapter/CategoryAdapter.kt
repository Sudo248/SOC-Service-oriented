package com.sudo248.soc.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.base_android.utils.DateUtils
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ItemCategoryBinding
import com.sudo248.soc.domain.entity.auth.Category
import com.sudo248.soc.ui.activity.main.adapter.callback.OnItemClickListener
import com.sudo248.soc.ui.uimodel.CategoryUiModel


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:37 - 12/03/2023
 */
class CategoryAdapter(
    private val onItemClick: (CategoryUiModel) -> Unit
) : BaseListAdapter<CategoryUiModel, CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(binding: ItemCategoryBinding) :
        BaseViewHolder<CategoryUiModel, ItemCategoryBinding>(binding) {
        override fun onBind(item: CategoryUiModel) {
            binding.category = item
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}