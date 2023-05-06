package com.sudo248.soc_staff.domain.repository

import android.provider.ContactsContract.RawContacts.Data
import com.sudo248.base_android.core.DataState
import com.sudo248.soc_staff.data.api.promotion.request.PromotionRequest
import com.sudo248.soc_staff.domain.entity.notification.Notification
import com.sudo248.soc_staff.domain.entity.promotion.Promotion

interface PromotionRepository {
    suspend fun getAllPromotion(): DataState<List<Promotion>, Exception>
    suspend fun postPromotion(promotion: PromotionRequest): DataState<Promotion, Exception>
    suspend fun putPromotion(promotion: Promotion): DataState<Promotion, Exception>
    suspend fun deletePromotion(promotionId: String): DataState<Boolean, Exception>

    suspend fun sendNotification(notification: Notification): DataState<String, Exception>
}