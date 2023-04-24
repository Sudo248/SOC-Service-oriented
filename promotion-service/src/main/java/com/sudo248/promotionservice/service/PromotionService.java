package com.sudo248.promotionservice.service;

import com.sudo248.promotionservice.controller.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    List<PromotionDto> getAllPromotions();
    PromotionDto getPromotionById(String promotionId);
    PromotionDto addPromotion(PromotionDto promotionDto);
    PromotionDto updatePromotion(PromotionDto promotionDto, String promotionId);
    boolean deletePromotion(String promotionId);


}
