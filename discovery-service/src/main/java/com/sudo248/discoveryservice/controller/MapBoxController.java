package com.sudo248.discoveryservice.controller;

import com.sudo248.discoveryservice.controller.dto.mapbox.MapBoxDistanceDto;
import com.sudo248.discoveryservice.external.MapBoxService;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapbox")
@Slf4j
public class MapBoxController {

    private final MapBoxService mapBoxService;

    public MapBoxController(MapBoxService mapBoxService) {
        this.mapBoxService = mapBoxService;
    }

    @GetMapping("/{fromLongitude},{fromLatitude},{toLongitude},{toLatitude}")
    public ResponseEntity<BaseResponse<?>> getDistance(
            @PathVariable("fromLongitude") double fromLongitude,
            @PathVariable("fromLatitude") double fromLatitude,
            @PathVariable("toLongitude") double toLongitude,
            @PathVariable("toLatitude") double toLatitude
    ) {
        return Utils.handleException(() -> {
            MapBoxDistanceDto mapBoxDistanceDto = mapBoxService.getDistance(fromLongitude, fromLatitude, toLongitude, toLatitude);
            log.error("Sudoo: " + mapBoxDistanceDto.getRoutes().get(0).getDuration() + " " + mapBoxDistanceDto.getRoutes().get(0).getDistance());
            if (mapBoxDistanceDto == null) {
                return BaseResponse.status(HttpStatus.BAD_REQUEST, "Does not exist category");
            }
            return BaseResponse.ok(mapBoxDistanceDto);
        });
    }

}
