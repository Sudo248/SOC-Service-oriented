package com.sudo248.soc.domain.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.soc.domain.entity.promotion.Promotion

interface PromotionRepository {
    suspend fun getAllPromotion(): DataState<List<Promotion>, Exception>
    suspend fun getPromotionById(promotionId: String): DataState<Promotion, Exception>
}