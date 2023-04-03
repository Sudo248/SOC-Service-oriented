package com.sudo248.promotionservice.controller.dto;

import java.io.Serializable;

public class PromotionDto  {
    private String promotionId;
    private String name;
    private Double value;

    public PromotionDto() {
    }

    public PromotionDto(String promotionId, String name, Double value) {
        this.promotionId = promotionId;
        this.name = name;
        this.value = value;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
