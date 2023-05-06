package com.sudo248.soc_staff.ui.activity.main.fragment.promotion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.soc_staff.data.api.promotion.request.PromotionRequest
import com.sudo248.soc_staff.domain.entity.notification.Notification
import com.sudo248.soc_staff.domain.entity.promotion.Promotion
import com.sudo248.soc_staff.domain.repository.PromotionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromotionViewModel @Inject constructor(
    private val promotionRepository: PromotionRepository
): BaseViewModel<NavDirections>() {

    var error: SingleEvent<String?> = SingleEvent(null)

    var titleNotification: String? = null

    private val _promotions = MutableLiveData<List<Promotion>> ()
    val promotions: LiveData<List<Promotion>> = _promotions

    init {
        getAllPromotion()
    }

    fun getAllPromotion() = launch {
        emitState(UiState.LOADING)
        promotionRepository.getAllPromotion()
            .onSuccess {
                _promotions.postValue(it)
            }
            .onError {
                error = SingleEvent(it.message)
            }.bindUiState(_uiState)
    }

    fun postPromotion(promotion: Promotion) = launch {
        emitState(UiState.LOADING)
        promotionRepository.postPromotion(PromotionRequest(
            value = promotion.value,
            name = promotion.name
        ))
            .onSuccess {
                titleNotification = it.name
                getAllPromotion()
            }
            .onError {
                error = SingleEvent(it.message)
                emitState(UiState.ERROR)
            }
    }

    fun putPromotion(promotion: Promotion) = launch {
        emitState(UiState.LOADING)
        promotionRepository.putPromotion(promotion)
            .onSuccess {
                titleNotification = it.name
                getAllPromotion()
            }
            .onError {
                error = SingleEvent(it.message)
                emitState(UiState.ERROR)
            }
    }

    fun deletePromotion(promotionId: String) = launch {
        emitState(UiState.LOADING)
        promotionRepository.deletePromotion(promotionId)
            .onSuccess {
                getAllPromotion()
            }
            .onError {
                error = SingleEvent(it.message)
                emitState(UiState.ERROR)
            }
    }

    fun sendNotification(notification: Notification) = launch(Dispatchers.IO) {
        promotionRepository.sendNotification(notification)
    }

}