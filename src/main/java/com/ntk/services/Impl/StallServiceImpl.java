package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.*;
import com.ntk.repositories.StallRepository;
import com.ntk.services.StallProductService;
import com.ntk.services.StallService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StallServiceImpl implements StallService {
    @Autowired
    private StallRepository stallRepository;

    @Autowired
    private StallProductService stallProductService;

    @Override
    @Transactional
    public List<JSONObject> getStallInfo(int... params){
        List<Stall> stalls= stallRepository.getStallInfo(params);
        List<JSONObject> jsonObjects = new ArrayList<>();
        stalls.forEach(e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("stallId", e.getStallId());
            jsonObject.put("stallName", e.getName());
            jsonObject.put("stallDescription", e.getDescription());
            jsonObject.put("stallImage",e.getImage());
            jsonObject.put("stallProductListAmount",
                    stallRepository.getStallProductListAmount(e.getStallId()));
            jsonObjects.add(jsonObject);
        });

        return jsonObjects;
    }

    @Override
    @Transactional
    public JSONObject getStallAmount() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stallAmount", stallRepository.getStallAmount());
        return jsonObject;
    }

    @Override
    @Transactional
    public JSONObject getProductListInfo(int stallId, int... params) {
        List<Product> products = stallRepository.getProductList(stallId, params);
        products.forEach(e-> System.out.println(e.getName()));
        //product list
        List<JSONObject> productJsonObjects = new ArrayList<>();
        products.forEach(e->{
            JSONObject productJsonObject = new JSONObject();
            productJsonObject.put("productId", e.getProductId());
            productJsonObject.put("productName", e.getName());
            productJsonObject.put("productImage",e.getImage());
            List<JSONObject> unitJsonObjects =new ArrayList<>();
            stallRepository.getProductUnitList(e.getProductId()).forEach(e1->{
                Unit unit = e1.getUnit();
                JSONObject unitJsonObject = new JSONObject();
                unitJsonObject.put("unitName", unit.getName());
                unitJsonObject.put("unitPrice", e1.getUnitPrice());
                unitJsonObjects.add(unitJsonObject);
            });
            productJsonObject.put("productUnits", unitJsonObjects);
            productJsonObject.put("productCategory", e.getCategory().getName());
            productJsonObjects.add(productJsonObject);
        });
        productJsonObjects.sort((e1, e2) -> {
            int eId1 = (int) e1.get("productId");
            int eId2 = (int) e2.get("productId");
            return Integer.compare(eId1, eId2);
        });
        // stall detail
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productDetailList", productJsonObjects);
        System.out.println(jsonObject);
        return jsonObject;
    }

    @Override
    @Transactional
    public JSONObject getStallLocation(int stallId) {
        Location location = stallRepository.getStallLocation(stallId);
        JSONObject locationJsonObject = new JSONObject();
        locationJsonObject.put("locationLatitude",location.getLatitude());
        locationJsonObject.put("locationLongitude", location.getLongitude());
        locationJsonObject.put("locationName", location.getName());
        return locationJsonObject;
    }

    @Override
    @Transactional
    public JSONObject getStallName(int stallId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stallName", stallRepository.getStallName(stallId));
        return jsonObject;
    }

    @Override
    @Transactional
    public JSONObject getStallProductListAmount(int stallId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productListAmount", stallRepository.getStallProductListAmount(stallId));
        return jsonObject;
    }

    @Override
    @Transactional
    public boolean addStall(Stall stall) {
        return stallRepository.addStall(stall);
    }

    @Override
    @Transactional
    public boolean updateStall(Stall stall) {
        return stallRepository.updateStall(stall);
    }

    @Override
    @Transactional
    public boolean deleteStall(Stall stall) {
        Set<StallProduct> stallProductSet = stall.getStallProducts();

        if (!stallProductSet.isEmpty())
            stallProductSet.forEach(e -> stallProductService.deleteStallProduct(e));

        return stallRepository.deleteStall(stall);
    }

    @Override
    @Transactional
    public Stall getStall(int stallId, String ... params) {
        return stallRepository.getStall(stallId, params);
    }

    @Override
    @Transactional
    public JSONObject getStallDetail(int stallId){
        Stall stall = stallRepository.getStall(stallId);
        JSONObject jsonObject = new JSONObject();
        if(stall!=null) {
            jsonObject.put("stallId", UtilsController.encodeBase64(String.valueOf(stall.getStallId())));
            jsonObject.put("stallName", stall.getName());
            jsonObject.put("stallDescription", stall.getDescription());
            Location location = stall.getLocation();
            jsonObject.put("locationId", UtilsController.encodeBase64(String.valueOf(location.getLocationId())));
            jsonObject.put("locationName",location.getName());
            jsonObject.put("longitude", location.getLongitude());
            jsonObject.put("latitude", location.getLatitude());
        }
        return jsonObject;
    }

}
