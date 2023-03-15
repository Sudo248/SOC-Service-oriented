package com.sudo248.soc.ui.mapper

import androidx.databinding.ObservableField
import com.sudo248.soc.domain.entity.auth.Category
import com.sudo248.soc.ui.uimodel.CategoryUiModel


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 23:12 - 12/03/2023
 */

fun Category.toUiModel(): CategoryUiModel {
    return CategoryUiModel(
        categoryId = categoryId,
        name = ObservableField(name),
        imageUrl = ObservableField(imageUrl)
    )
}