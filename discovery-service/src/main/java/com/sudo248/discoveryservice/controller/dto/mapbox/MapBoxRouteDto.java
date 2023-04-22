package com.sudo248.discoveryservice.controller.dto.mapbox;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapBoxRouteDto {
    private String weight_name;
    public double weight;
    public double duration;
    public double distance;
    public ArrayList<Object> legs;
    public String geometry;
}
