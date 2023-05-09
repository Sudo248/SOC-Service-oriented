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
        if (cacheLocation.containsKey(userId) &&
                cacheLocation.get(userId).getLongitude() > 0.0 &&
                cacheLocation.get(userId).getLatitude() > 0.0
        ) {
            return cacheLocation.get(userId);
        } else {
            Location location = userService.getLocation(userId);
            saveLocation(userId, location);
            return location;
        }
    }

    public Location requiredGetLocation(String userId) {
        Location location = userService.getLocation(userId);
        saveLocation(userId, location);
        return location;
    }

    public String clearCache() {
        String res = getAllLocation();
        cacheLocation.clear();
        return res;
    }
    public String getAllLocation() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        for(String key : cacheLocation.keySet()) {
            res.append("\""+key+"\"").append(":").append(cacheLocation.get(key)).append(",");
        }
        res.append("}");
        return res.toString();
    }
}
