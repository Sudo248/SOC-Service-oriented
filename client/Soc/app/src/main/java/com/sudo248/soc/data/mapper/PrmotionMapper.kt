package com.sudo248.soc.data.mapper

import com.sudo248.soc.data.dto.promotion.PromotionDto
import com.sudo248.soc.domain.entity.promotion.Promotion

fun PromotionDto.toPromotion(): Promotion {
    return Promotion(
        promotionId, value, name
    )
}