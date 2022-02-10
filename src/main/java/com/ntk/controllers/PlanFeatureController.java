package com.ntk.controllers;

import com.ntk.services.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PlanFeatureController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/plan")
    public String planFeature(){
        return "plan-feature";
    }

    @PostMapping("/plan/api/productNameHint")
    public ResponseEntity<List<JSONObject>> getHint(
            @RequestBody Map<String, String> params){
        String keyword = params.get("keyword");
        return new ResponseEntity<>(
                productService.getProductNames(keyword), HttpStatus.OK);
    }

    @PostMapping("/plan/api/searchResult")
    public ResponseEntity<List<JSONObject>> getSearchResult(
            @RequestBody Map<String, String> params){
        String keyword = params.get("keyword");
        int offSet = Integer.parseInt(params.get("offSet"));
        int limit = Integer.parseInt(params.get("limit"));
        return new ResponseEntity<>(
                productService.getProductDetails(
                        keyword,offSet, limit,"category","stall","productUnits"),
                HttpStatus.OK);
    }
}
