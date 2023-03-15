package com.sudo248.soc.ui.activity.main.fragment.discovery

import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.soc.ui.activity.main.adapter.CategoryAdapter
import com.sudo248.soc.ui.uimodel.CategoryUiModel


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:34 - 12/03/2023
 */
class DiscoveryViewModel : BaseViewModel<NavDirections>() {
    val categoryAdapter = CategoryAdapter(::onCategoryItemClick)
    val categories: List<CategoryUiModel> = listOf()


    private fun onCategoryItemClick(item: CategoryUiModel) {

    }
}