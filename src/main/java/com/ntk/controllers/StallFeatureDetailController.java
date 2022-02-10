package com.ntk.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntk.pojos.*;
import com.ntk.services.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class StallFeatureDetailController {
    @Autowired
    private StallService stallService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UnitTypeService unitTypeService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductUnitService productUnitService;

    @RequestMapping("/stall/detail")
    public String stallDetail(Model model, @RequestParam Map<String, String> params) {
        try {
            int stallId = Integer.parseInt(UtilsController.decodeBase64(params.get("stallId")));
            model.addAttribute("stallLocation", stallService.getStallLocation(stallId));
            model.addAttribute("stallName", stallService.getStallName(stallId));
            return "stall-feature-stall-detail";
        } catch (Exception e) {
            return "redirect:/stall";
        }
    }

    @RequestMapping("/stall/detail/add")
    public String addProduct(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "stall-feature-stall-detail-add";
    }

    @RequestMapping(value = "/stall/detail/add", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    public String addProductData(HttpServletRequest httpServletRequest,
                                 @RequestParam(name = "stallId") String stallId,
                                 @RequestParam(name = "image", required = false) MultipartFile image) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String name = httpServletRequest.getParameter("name");
        int sId = Integer.parseInt(UtilsController.decodeBase64(stallId));
        int categoryId = Integer.parseInt(UtilsController.decodeBase64(
                httpServletRequest.getParameter("category")));
        if(httpServletRequest.getParameter("units")==null)
            return String.format("redirect:/stall/detail/add/?stallId=%s&error=%s",stallId,
                            UtilsController.encodeBase64("noUnit"));

        List<List<String>> units = UtilsController.splitStr(httpServletRequest.getParameter("units"),
                ";", ",");
        Stall stall = stallService.getStall(sId);
        Product product = new Product();
        product.setName(name);
        if(!image.isEmpty())
            try {
                Map url = cloudinary.uploader().upload(image.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "product"));
                product.setImage((String) url.get("secure_url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        else
           product.setImage(null);
        product.setCategory(categoryService.getCategory(categoryId));
        product.setStall(stall);
        boolean result=productService.addProduct(product);
        if(result) {
            Objects.requireNonNull(units).forEach(e -> {
                int unitId = Integer.parseInt(UtilsController.decodeBase64(e.get(0)));
                BigDecimal unitPrice = new BigDecimal(e.get(1));
                ProductUnit productUnit = new ProductUnit();
                ProductUnitId productUnitId = new ProductUnitId();
                productUnitId.setProductId(product.getProductId());
                productUnitId.setUnitId(unitId);
                productUnit.setProductUnitId(productUnitId);
                productUnit.setUnitPrice(unitPrice);
                productUnitService.addProductUnit(productUnit);

            });
        }
        return String.format("redirect:/stall/detail/add/?stallId=%s&error=%s",stallId,
                result?UtilsController.encodeBase64("successful"):
                        UtilsController.encodeBase64("fail"));
    }

    @RequestMapping("/stall/detail/edit")
    public String editProduct(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "stall-feature-stall-detail-edit";
    }


    @RequestMapping(value = "/stall/detail/edit", method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    public String editProductData(HttpServletRequest httpServletRequest,
                                  @RequestParam(name = "stallId") String stallId,
                                 @RequestParam(name = "productId") String productId,
                                 @RequestParam(name = "image", required = false) MultipartFile image) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String name = httpServletRequest.getParameter("name");
        int categoryId = Integer.parseInt(UtilsController.decodeBase64(
                httpServletRequest.getParameter("category")));
        int pId = Integer.parseInt(UtilsController.decodeBase64(productId));
        if (httpServletRequest.getParameter("units") == null)
            return String.format("redirect:/stall/detail/edit/?stallId=%s&error=%s", stallId,
                    UtilsController.encodeBase64("noUnit"));

        List<List<String>> units = UtilsController.splitStr(httpServletRequest.getParameter("units"),
                ";", ",");

        //update here
        Product product = productService.getProduct(pId);
        product.setName(name);
        if (!image.isEmpty())
            try {
                Map url = cloudinary.uploader().upload(image.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "product"));
                product.setImage((String) url.get("secure_url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        else
            product.setImage(null);
        product.setCategory(categoryService.getCategory(categoryId));
        boolean result = productService.updateProduct(product);
        if (result) {
            productUnitService.deleteProductUnit(pId);
            Objects.requireNonNull(units).forEach(e -> {
                int unitId = Integer.parseInt(UtilsController.decodeBase64(e.get(0)));
                BigDecimal unitPrice = new BigDecimal(e.get(1));
                ProductUnit productUnit = new ProductUnit();
                ProductUnitId productUnitId = new ProductUnitId();
                productUnitId.setProductId(product.getProductId());
                productUnitId.setUnitId(unitId);
                productUnit.setProductUnitId(productUnitId);
                productUnit.setUnitPrice(unitPrice);
                productUnitService.addProductUnit(productUnit);

            });

        }
        return String.format("redirect:/stall/detail/edit/?stallId=%s&error=%s", stallId,
                result ? UtilsController.encodeBase64("successful") :
                        UtilsController.encodeBase64("fail"));
    }

    @PostMapping("/stall/stall-detail/api/productListInfo")
    public ResponseEntity<JSONObject> getStallDetailInfo(
            @RequestBody Map<String, String> params) {
        int stallId = Integer.parseInt(params.get("stallId"));
        int offSet = -1;
        int limit = -1;
        if (params.get("offSet") != null && params.get("limit") != null) {
            offSet = Integer.parseInt(params.get("offSet"));
            limit = Integer.parseInt(params.get("limit"));
        }
        return new ResponseEntity<>(stallService.getProductListInfo(stallId, offSet, limit),
                HttpStatus.OK);
    }

    @PostMapping("/stall/stall-detail/api/productListAmount")
    public ResponseEntity<JSONObject> getStallProductListAmount(
            @RequestBody Map<String, String> params) {
        int stallId = Integer.parseInt(params.get("stallId"));
        return new ResponseEntity<>(stallService.getStallProductListAmount(stallId),
                HttpStatus.OK);
    }

    @GetMapping("/stall/stall-detail/api/unitType")
    public ResponseEntity<List<JSONObject>> getUnitTypes() {
        return new ResponseEntity<>(unitTypeService.getUnitTypes(), HttpStatus.OK);
    }

    @PostMapping("/stall/stall-detail/api/unitData")
    public ResponseEntity<JSONObject> getUnitType(
            @RequestBody Map<String, String> params){
        String unitTypeId = params.get("unitTypeId");
        return new ResponseEntity<>(
                unitTypeService.getUnitType(Integer.parseInt(UtilsController.decodeBase64(unitTypeId)),
                        "units"),
                HttpStatus.OK);
    }

    @PostMapping("/stall/stall-detail/edit/api/productDetail")
    public ResponseEntity<JSONObject> getProductInfo(
            @RequestBody Map<String, String> params){
        String productId = params.get("productId");
        return new ResponseEntity<>(
                productService.getProductDetail(Integer.parseInt(UtilsController.decodeBase64(productId)),
                        "category", "productUnits"),
                HttpStatus.OK
        );
    }
    @PostMapping("/stall/stall-detail/api/deleteProduct")
    public ResponseEntity<JSONObject> deleteStall(@RequestBody Map<String, String> params){
        String productId = UtilsController.decodeBase64(params.get("productId"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", productService.deleteProduct(Integer.parseInt(productId)));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
