package com.ntk.services;

import com.ntk.pojos.Location;
import org.json.simple.JSONObject;

import java.util.Set;

public interface LocationService {
    boolean addLocation(Location location);
    Location getLocationObj(int locationId);
    Set<Location> getLocationsOfCurrentUser();
    JSONObject getLocation(int locationId);
    Set<JSONObject> getLocationsOfCurrentUser(Set<Location> locations);
    Set<JSONObject> getProductLocationCurrentUser();
}
