package com.ntk.controllers;

import com.ntk.services.StallService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class StallFeatureDetailController {
    @Autowired
    private StallService stallService;

    @RequestMapping("/stall/detail")
    public String stallDetail(Model model, @RequestParam Map<String, String> params){
        try{
            int stallId =  Integer.parseInt(UtilsController.decodeBase64(params.get("stallId")));
            model.addAttribute("stallLocation", stallService.getStallLocation(stallId));
            model.addAttribute("stallName", stallService.getStallName(stallId));
            return "stall-feature-stall-detail";
        }
        catch (Exception e){
            return "redirect:/stall";
        }
    }

    @RequestMapping("/stall/detail/add")
    public String addProduct(@RequestParam Map<String, String> params){
        return "stall-feature-stall-detail-add";
    }

    @RequestMapping("/stall/detail/edit")
    public String editProduct(Model model,
                            @RequestParam(value = "stallId") String param,
                            @RequestParam Map<String, String> params){
        return "stall-feature-stall-detail-edit";
    }

    @PostMapping("/stall/stall-detail/api/productListInfo")
    public ResponseEntity<JSONObject> getStallDetailInfo(
            @RequestBody Map<String, String> params){
        int stallId = Integer.parseInt(params.get("stallId"));
        int offSet = -1;
        int limit = -1;
        if(params.get("offSet") !=null && params.get("limit")!=null) {
            offSet= Integer.parseInt(params.get("offSet"));
            limit = Integer.parseInt(params.get("limit"));
        }
        return new ResponseEntity<>(stallService.getProductListInfo(stallId, offSet, limit),
                HttpStatus.OK);
    }

    @PostMapping("/stall/stall-detail/api/productListAmount")
    public ResponseEntity<JSONObject> getStallProductListAmount(
            @RequestBody Map<String, String> params){
        int stallId = Integer.parseInt(params.get("stallId"));
        return new ResponseEntity<>(stallService.getStallProductListAmount(stallId),
                HttpStatus.OK);
    }
}
