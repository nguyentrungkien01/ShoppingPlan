package com.ntk.controllers;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntk.pojos.Location;
import com.ntk.pojos.Stall;
import com.ntk.pojos.User;
import com.ntk.services.AccountService;
import com.ntk.services.LocationService;
import com.ntk.services.StallService;
import com.ntk.services.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
public class StallFeatureController {
    @Autowired
    private StallService stallService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserService userService;

    @RequestMapping("/stall")
    public String stallFeature(){
        return "stall-feature";
    }

    @RequestMapping("/stall/add")
    public String addStall(Model model){
        Set<JSONObject> locations = locationService.getLocationsOfCurrentUser(
                locationService.getLocationsOfCurrentUser());
        model.addAttribute("locations", locations);
        return "stall-feature-add";
    }

    @RequestMapping(path= "/stall/add", method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
    public String addStallData(HttpServletRequest httpServletRequest,
                               @RequestParam("image") MultipartFile image){
        String name = UtilsController.parseUTF8(httpServletRequest.getParameter("name"));
        String locationId = UtilsController.decodeBase64(UtilsController.parseUTF8(
                httpServletRequest.getParameter("location")));
        Stall stall= new Stall();
        stall.setName(name);
        stall.setLocation(locationService.getLocationObj(Integer.parseInt(locationId)));
        stall.setUser(userService.getCurrentUser());
        if(!image.isEmpty()) {
            try {
                Map url = cloudinary.uploader().upload(image.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "stall"));
                stall.setImage((String) url.get("secure_url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            stall.setImage(null);
        if(!httpServletRequest.getParameter("description").isEmpty())
            stall.setDescription(UtilsController.parseUTF8(
                        httpServletRequest.getParameter("description")));
        else
            stall.setDescription(null);
        boolean result = stallService.addStall(stall);
        return String.format("redirect:/stall/add/?error=%s",
                result?UtilsController.encodeBase64("successful"):
                        UtilsController.encodeBase64("fail"));
    }

    @RequestMapping("/stall/add/location")
    public String addStallLocation(){
        return "stall-feature-add-location";
    }

    @RequestMapping(path= "/stall/add/location", method =RequestMethod.POST)
    public String addStallLocation(Model model , HttpServletRequest httpServletRequest){
        String locationName = UtilsController.parseUTF8(httpServletRequest.getParameter("locationName"));
        String longitude = UtilsController.parseUTF8(httpServletRequest.getParameter("longitude"));
        String latitude = UtilsController.parseUTF8(httpServletRequest.getParameter("latitude"));
        if(longitude.isEmpty() || latitude.isEmpty())
            return String.format("redirect:/stall/add/location/?error=%s",
                    UtilsController.encodeBase64("noLocationData"));
        Location location = new Location();
        location.setName(locationName);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setUser(userService.getCurrentUser());
        boolean result = locationService.addLocation(location);
        return String.format("redirect:/stall/add/location/?error=%s",
                result? UtilsController.encodeBase64("successful"):
                        UtilsController.encodeBase64("fail"));
    }


    @RequestMapping("/stall/edit")
    public String editStall(Model model){
        Set<JSONObject> locations = locationService.getLocationsOfCurrentUser(
                locationService.getLocationsOfCurrentUser());
        model.addAttribute("locations", locations);
        return "stall-feature-edit";
    }

    @RequestMapping(path="/stall/edit", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public String editStallData(HttpServletRequest httpServletRequest,
                                @RequestParam("image") MultipartFile image,
                                @RequestParam("stallId") String stallId){
        String name = UtilsController.parseUTF8(httpServletRequest.getParameter("name"));
        String locationId = UtilsController.decodeBase64(UtilsController.parseUTF8(
                httpServletRequest.getParameter("location")));
        Stall stall = stallService.getStall(Integer.parseInt(UtilsController.decodeBase64(stallId)));
        stall.setName(name);
        Location location= locationService.getLocationObj(Integer.parseInt(locationId));
        stall.setLocation(location);
        if(!image.isEmpty()) {
            try {
                Map url = cloudinary.uploader().upload(image.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "stall"));
                stall.setImage((String) url.get("secure_url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            stall.setImage(null);
        if(!httpServletRequest.getParameter("description").isEmpty())
            stall.setDescription(UtilsController.parseUTF8(
                    httpServletRequest.getParameter("description")));
        else
            stall.setDescription(null);
        boolean result=stallService.updateStall(stall);
        return String.format("redirect:/stall/edit/?stallId=%s&error=%s",stallId,
                result?UtilsController.encodeBase64("successful"):
                        UtilsController.encodeBase64("fail"));
    }


    @PostMapping("/stall/api/stallInfo")
    public ResponseEntity<List<JSONObject>> getStallInfo(
            @RequestBody Map<String, String> params){
        int offSet = -1;
        int limit = -1;
        if(params.get("offSet") !=null && params.get("limit")!=null) {
            offSet= Integer.parseInt(params.get("offSet"));
            limit = Integer.parseInt(params.get("limit"));
        }
        return new ResponseEntity<>(stallService.getStallInfo(offSet, limit),
                HttpStatus.OK);
    }

    @GetMapping("/stall/api/stallAmount")
    public ResponseEntity<JSONObject> getStallAmount(){
        return new ResponseEntity<>(stallService.getStallAmount(),
                HttpStatus.OK);
    }

    @PostMapping("/stall/api/lngLatData")
    public ResponseEntity<JSONObject> getStallLngLat(@RequestBody Map<String, String> params){
        return  new ResponseEntity<>(locationService.getLocation(Integer.parseInt(params.get("locationId"))),
                HttpStatus.OK);
    }

    @PostMapping("/stall/edit/api/data")
    public  ResponseEntity<JSONObject> getStallDetail(@RequestBody Map<String, String> params){
        String stallId = params.get("stallId");
        return new ResponseEntity<>(stallService.getStallDetail(Integer.parseInt(stallId)),
                HttpStatus.OK);
    }

    @PostMapping("/stall/api/deleteStall")
    public ResponseEntity<JSONObject> deleteStall(@RequestBody Map<String, String> params){
        String stallId = UtilsController.decodeBase64(UtilsController.parseUTF8(params.get("stallId")));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", stallService.deleteStall(
                stallService.getStall(Integer.parseInt(stallId), "stallProducts")));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
