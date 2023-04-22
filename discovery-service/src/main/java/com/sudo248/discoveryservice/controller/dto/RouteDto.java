package com.sudo248.discoveryservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private double weight = 0.0;
    private ValueDto duration;
    private ValueDto distance;
}
