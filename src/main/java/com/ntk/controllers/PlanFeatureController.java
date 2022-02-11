package com.ntk.controllers;

import com.ntk.services.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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

    @RequestMapping(path = "/plan/route")
    public String routeFeature(){
        return "route";
    }

    @RequestMapping(path= "/plan/route", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public String routeMap(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        List<List<String>> productIds = UtilsController.splitStr(httpServletRequest.getParameter("products"), ",");
        // code here
        
        return "redirect:/plan/route";
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

    @PostMapping("/plan/api/amountSearchResult")
    public ResponseEntity<JSONObject> getAmountSearchResult(
            @RequestBody Map<String, String> params){
        String keyword = params.get("keyword");
        return new ResponseEntity<>(
                productService.getAmountProductDetails(keyword),
                HttpStatus.OK);
    }
}
