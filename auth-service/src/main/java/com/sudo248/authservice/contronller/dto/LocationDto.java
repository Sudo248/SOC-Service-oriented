package com.sudo248.authservice.contronller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private double longitude = 0;
    private double latitude = 0;
}
