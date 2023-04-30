package com.sudo248.invoiceservice.internal;

import com.sudo248.invoiceservice.Controller.dto.PromotionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PROMOTION-SERVICE")
@Service
public interface PromotionService {

    @GetMapping("api/v1/promotion/service/{promotionId}")
    PromotionDto getPromotionById(@PathVariable("promotionId") String promotionId);

}
