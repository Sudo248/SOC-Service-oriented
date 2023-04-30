package com.sudo248.promotionservice.controller;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.promotionservice.controller.dto.PromotionDto;
import com.sudo248.promotionservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> addPromotion(@RequestBody PromotionDto promotionDto) {
        PromotionDto savedPromotion = promotionService.addPromotion(promotionDto);
        return BaseResponse.ok(savedPromotion);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllPromotions() {
        List<PromotionDto> promotionDtos = promotionService.getAllPromotions();
        return BaseResponse.ok(promotionDtos);
    }
    @GetMapping("/{promotionId}")
    public ResponseEntity<BaseResponse<?>> getPromotionById(@PathVariable String promotionId) {
        PromotionDto promotion = promotionService.getPromotionById(promotionId);
        return BaseResponse.ok(promotion);
    }
    @DeleteMapping("/{promotionId}")
    public ResponseEntity<BaseResponse<?>> deletePromotionById(@PathVariable String promotionId) {
        boolean success = promotionService.deletePromotion(promotionId);
        return BaseResponse.ok(null);
    }
    @PutMapping("/{promotionId}")
    public ResponseEntity<BaseResponse<?>> updatePromotionById(@RequestBody PromotionDto promotionDto,@PathVariable String promotionId) {
        PromotionDto promotion = promotionService.updatePromotion(promotionDto,promotionId);
        return BaseResponse.ok(promotion);
    }

    //call from other service
    @GetMapping("/service/{promotionId}")
    public PromotionDto getPromotionByIdFromService(@PathVariable String promotionId) {
        return promotionService.getPromotionById(promotionId);
    }
}
