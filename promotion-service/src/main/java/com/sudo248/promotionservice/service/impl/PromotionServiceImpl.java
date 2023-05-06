package com.sudo248.promotionservice.service.impl;

import com.sudo248.domain.util.Utils;
import com.sudo248.promotionservice.controller.dto.PromotionDto;
import com.sudo248.promotionservice.repository.PromotionRepository;
import com.sudo248.promotionservice.repository.entity.Promotion;
import com.sudo248.promotionservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotionList = promotionRepository.findAll();
        List<PromotionDto> promotionDtoList = new ArrayList<>();
        for(Promotion promotion: promotionList){
            PromotionDto promotionDto = new PromotionDto();
            promotionDto.setPromotionId(promotion.getPromotionId());
            promotionDto.setName(promotion.getName());
            promotionDto.setValue(promotion.getValue());
            promotionDtoList.add(promotionDto);
        }
        return promotionDtoList;
    }

    @Override
    public PromotionDto getPromotionById(String promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).get();
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setPromotionId(promotion.getPromotionId());
        promotionDto.setName(promotion.getName());
        promotionDto.setValue(promotion.getValue());
        System.out.println(promotionDto.getPromotionId() + " " + promotionDto.getName() + " " + promotionDto.getValue());
        return promotionDto;
    }

    @Override
    public PromotionDto addPromotion(PromotionDto promotionDto) {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(Utils.createIdOrElse(promotionDto.getPromotionId()));
        promotion.setName(promotionDto.getName());
        promotion.setValue(promotionDto.getValue());
        promotionRepository.save(promotion);
        promotionDto.setPromotionId(promotion.getPromotionId());
        return promotionDto;
    }

    @Override
    public PromotionDto updatePromotion(PromotionDto promotionDto, String promotionId) {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(promotionId);
        promotion.setName(promotionDto.getName());
        promotion.setValue(promotionDto.getValue());
        promotionRepository.save(promotion);

        Map<String, String> vars = new HashMap<>();

//        vars.put("field", "promotion");
//        vars.put("fieldId", promotionId);
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8084/api/v1/invoice/updateInvoice/{field}/{fieldId}";
//        restTemplate.put(url,null, vars);
        return promotionDto;
    }

    @Override
    public boolean deletePromotion(String promotionId) {
        if( promotionRepository.findById(promotionId) == null)
            return false;
        else{
            promotionRepository.deleteById(promotionId);
        }
        return true;
    }
}
