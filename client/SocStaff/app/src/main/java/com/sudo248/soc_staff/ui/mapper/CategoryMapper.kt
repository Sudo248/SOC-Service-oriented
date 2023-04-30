package com.sudo248.soc_staff.ui.mapper

import com.sudo248.soc_staff.domain.entity.discovery.Category
import com.sudo248.soc_staff.ui.uimodel.CategoryUiModel


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 23:12 - 12/03/2023
 */

fun Category.toCategoryUi(): CategoryUiModel {
    return CategoryUiModel(
        categoryId = categoryId,
        name = name,
        imageUrl = imageUrl
    )
}