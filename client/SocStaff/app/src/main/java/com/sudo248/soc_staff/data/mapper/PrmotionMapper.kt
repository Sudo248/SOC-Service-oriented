package com.sudo248.soc_staff.data.mapper

import com.sudo248.soc_staff.data.dto.promotion.PromotionDto
import com.sudo248.soc_staff.domain.entity.promotion.Promotion

fun PromotionDto.toPromotion(): Promotion {
    return Promotion(
        promotionId, value, name
    )
}


fun Promotion.toPromotionDto(): PromotionDto {
    return PromotionDto(
        promotionId, value, name
    )
}