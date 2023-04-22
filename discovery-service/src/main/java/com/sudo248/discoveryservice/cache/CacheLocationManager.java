package com.sudo248.discoveryservice.cache;

import com.sudo248.discoveryservice.internal.UserService;
import com.sudo248.discoveryservice.repository.entity.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CacheLocationManager {
    public static final Map<String, Location> cacheLocation = new HashMap<>();

    private final UserService userService;

    public CacheLocationManager(UserService userService) {
        this.userService = userService;
    }

    public void saveLocation(String userId, String location) {
        if (location.isEmpty() || location.isBlank()) return;
        String[] locations = location.split(",");
        if (locations.length <=1) return;
        try {
            Location _location = new Location(Double.parseDouble(locations[0].trim()), Double.parseDouble(locations[1].trim()));
            saveLocation(userId, _location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveLocation(String userId, Location location) {
        cacheLocation.put(userId, location);
        log.info("Cache location: " + location);
    }

    public Location getLocation(String userId) {
        if (cacheLocation.containsKey(userId)) {
            return cacheLocation.get(userId);
        } else {
            Location location = userService.getLocation(userId);
            cacheLocation.put(userId, location);
            log.info("Cache location: " + location);
            return location;
        }
    }
}
