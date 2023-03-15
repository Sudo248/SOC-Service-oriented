package com.sudo248.soc.domain.entity.auth

import com.sudo248.base_android.base.ItemDiff
import com.sudo248.soc.domain.entity.Product


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:43 - 12/03/2023
 */
data class Category(
    val categoryId: String,
    val name: String,
    val imageUrl: String,
    val products: List<Product>
)