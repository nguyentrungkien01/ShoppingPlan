package com.ntk.services.Impl;


import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Location;
import com.ntk.pojos.Product;
import com.ntk.pojos.Stall;
import com.ntk.pojos.UserProduct;
import com.ntk.repositories.*;
import com.ntk.services.AccountService;
import com.ntk.services.LocationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StallRepository stallRepository;

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
        return userRepository.getUser(accountRepository.getAccount(
                        UtilsController.getCurrentUsername()).getUser().getUserId(), "locations")
                .getLocations();
    }

    @Override
    @Transactional
    public JSONObject getLocation(int locationId) {
        Location location = locationRepository.getLocation(locationId);
        JSONObject jsonObject = new JSONObject();
        if (location != null) {
            jsonObject.put("longitude", location.getLongitude());
            jsonObject.put("latitude", location.getLatitude());
        }
        return jsonObject;
    }

    @Override
    @Transactional
    public Set<JSONObject> getLocationsOfCurrentUser(Set<Location> locations) {
        Set<JSONObject> jsonObjects = new HashSet<>();
        locations.forEach(e -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("locationId", UtilsController.encodeBase64(String.valueOf(e.getLocationId())));
            jsonObject.put("locationName", e.getName());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }

    @Override
    @Transactional
    public Set<JSONObject> getProductLocationCurrentUser() {
        List<UserProduct> userProducts = userProductRepository.getUserProducts(accountRepository.getAccount(
                UtilsController.getCurrentUsername()).getUser(), "product");
        List<Product> products = new ArrayList<>();
        userProducts.forEach(e -> products.add(productRepository.getProduct(
                e.getProduct().getProductId(), "stall")));
        Set<Stall> stalls = new HashSet<>();
        products.forEach(e -> stalls.add(stallRepository.getStall(e.getStall().getStallId(), "location")));
        Set<Location> locations = new HashSet<>();
        stalls.forEach(e -> locations.add(e.getLocation()));
        Set<JSONObject> jsonObjects = new HashSet<>();
        locations.forEach(e -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", UtilsController.encodeBase64(String.valueOf(e.getLocationId())));
            jsonObject.put("longitude", e.getLongitude());
            jsonObject.put("latitude", e.getLatitude());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }

    @Override
    @Transactional
    public List<JSONObject> getProductsOfLocation(List<Integer> locationIds) {
        List<UserProduct> userProducts = userProductRepository.getUserProducts(accountRepository.getAccount(
                UtilsController.getCurrentUsername()).getUser(), "product");
        List<JSONObject> jsonObjects = new ArrayList<>();
        locationIds.forEach(lId -> {
            Location location = locationRepository.getLocation(lId, "stalls");
            Set<String> productNames = new HashSet<>();
            location.getStalls().forEach(e -> {
                Stall stall = stallRepository.getStall(e.getStallId(), "products");
                Set<Product> productsStall = stall.getProducts();
                userProducts.forEach(e1 -> {
                    productsStall.forEach(e2 -> {
                        if (e1.getProduct().getProductId() == e2.getProductId())
                            productNames.add(e1.getProduct().getName());
                    });

                });
            });
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productName", productNames);
            jsonObjects.add(jsonObject);
        });

        return jsonObjects;
    }
}
