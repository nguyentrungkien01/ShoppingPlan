package com.ntk.services.Impl;


import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Location;
import com.ntk.repositories.AccountRepository;
import com.ntk.repositories.LocationRepository;
import com.ntk.repositories.UserRepository;
import com.ntk.services.AccountService;
import com.ntk.services.LocationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public boolean addLocation(Location location) {
        return locationRepository.addLocation(location);
    }

    @Override
    @Transactional
    public Location getLocationObj(int locationId) {
        return locationRepository.getLocation(locationId);
    }

    @Override
    @Transactional
    public Set<Location> getLocationsOfCurrentUser() {
        return userRepository.getUser( accountRepository.getAccount(
                UtilsController.getCurrentUsername()).getUser().getUserId(), "locations")
                .getLocations();
    }

    @Override
    @Transactional
    public JSONObject getLocation(int locationId) {
        Location location = locationRepository.getLocation(locationId);
        JSONObject jsonObject= new JSONObject();
        if (location!=null) {
            jsonObject.put("longitude", location.getLongitude());
            jsonObject.put("latitude", location.getLatitude());
        }
        return jsonObject;
    }

    @Override
    @Transactional
    public Set<JSONObject> getLocationsOfCurrentUser(Set<Location> locations) {
        Set<JSONObject> jsonObjects = new HashSet<>();
        locations.forEach(e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("locationId", UtilsController.encodeBase64(String.valueOf(e.getLocationId())));
            jsonObject.put("locationName", e.getName());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }
}
