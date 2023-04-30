package com.sudo248.promotionservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto  {
    private String promotionId;
    private String name;
    private Double value;
}
