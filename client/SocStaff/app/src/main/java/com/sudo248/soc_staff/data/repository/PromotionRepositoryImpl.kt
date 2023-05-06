package com.sudo248.soc_staff.data.repository

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.data.api.handleResponse
import com.sudo248.base_android.ktx.stateOn
import com.sudo248.soc_staff.data.api.notification.NotificationService
import com.sudo248.soc_staff.data.api.promotion.PromotionService
import com.sudo248.soc_staff.data.api.promotion.request.PromotionRequest
import com.sudo248.soc_staff.data.ktx.data
import com.sudo248.soc_staff.data.ktx.errorBody
import com.sudo248.soc_staff.data.mapper.toPromotion
import com.sudo248.soc_staff.data.mapper.toPromotionDto
import com.sudo248.soc_staff.domain.entity.notification.Notification
import com.sudo248.soc_staff.domain.entity.promotion.Promotion
import com.sudo248.soc_staff.domain.repository.PromotionRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromotionRepositoryImpl @Inject constructor(
    private val promotionService: PromotionService,
    private val notificationService: NotificationService,
    private val ioDispatcher: CoroutineDispatcher,
) : PromotionRepository {
    override suspend fun getAllPromotion(): DataState<List<Promotion>, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(promotionService.getAllPromotion())
        if (response.isSuccess) {
            response.data().map { it.toPromotion() }
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun postPromotion(promotion: PromotionRequest): DataState<Promotion, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(promotionService.postPromotion(promotion))
        if (response.isSuccess) {
            response.data().toPromotion()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun putPromotion(promotion: Promotion): DataState<Promotion, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(promotionService.putPromotion(promotion.promotionId, promotion.toPromotionDto()))
        if (response.isSuccess) {
            response.data().toPromotion()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun deletePromotion(promotionId: String): DataState<Boolean, Exception> = stateOn(ioDispatcher){
        val response = handleResponse(promotionService.deletePromotion(promotionId))
        if (response.isSuccess) {
            response.data()
        } else {
            throw response.error().errorBody()
        }
    }

    override suspend fun sendNotification(notification: Notification): DataState<String, Exception>  = stateOn(ioDispatcher){
        val response = handleResponse(notificationService.sendNotificationPromotion(notification))
        if (response.isSuccess) {
            response.data()
        } else {
            throw response.error().errorBody()
        }
    }
}