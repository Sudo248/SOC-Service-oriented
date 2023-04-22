package com.sudo248.discoveryservice.controller.dto.mapbox;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapBoxDistanceDto {
    private ArrayList<MapBoxRouteDto> routes;
    private ArrayList<Object> waypoints;
    private String code;
    private String uuid;
}
