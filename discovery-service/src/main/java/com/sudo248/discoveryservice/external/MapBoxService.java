package com.sudo248.discoveryservice.external;

import com.sudo248.discoveryservice.controller.dto.mapbox.MapBoxDistanceDto;
import com.sudo248.domain.common.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MAPBOX", url = "https://api.mapbox.com/directions/v5/mapbox/driving")
@Service
public interface MapBoxService {
    @GetMapping("/{fromLongitude}%2C{fromLatitude}%3B{toLongitude}%2C{toLatitude}?alternatives=false&continue_straight=false&exclude=motorway&geometries=polyline&overview=simplified&steps=false&access_token=" + Constants.MAPBOX_ACCESS_TOKEN)
    MapBoxDistanceDto getDistance(
            @PathVariable("fromLongitude") double fromLongitude,
            @PathVariable("fromLatitude") double fromLatitude,
            @PathVariable("toLongitude") double toLongitude,
            @PathVariable("toLatitude") double toLatitude
    );

}
