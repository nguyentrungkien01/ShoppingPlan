package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.*;
import com.ntk.repositories.*;
import com.ntk.services.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUnitRepository productUnitRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private StallRepository stallRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    @Transactional
    public boolean deleteProduct(int productId) {
        Product product = productRepository.getProduct(productId, "userProducts","productUnits");
        if(product==null)
            return false;
        product.getProductUnits().forEach(e->productUnitRepository.deleteProductUnit(e));
        product.getUserProducts().forEach(e->userProductRepository.deleteUserProduct(e));
        return productRepository.deleteProduct(product);
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    @Transactional
    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    @Override
    @Transactional
    public JSONObject getProductDetail(int productId, String... params) {
        Product product = productRepository.getProduct(productId, params);
        if (product == null)
            return null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", product.getName());
        jsonObject.put("categoryId", UtilsController.encodeBase64(
                String.valueOf(product.getCategory().getCategoryId())));
        List<JSONObject> units = new ArrayList<>();
        product.getProductUnits().forEach(e -> {
            ProductUnit productUnit = productUnitRepository.getProductUnit(
                    e.getProductUnitId(), "unit");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("unitId", UtilsController.encodeBase64(String.valueOf(
                    productUnit.getUnit().getUnitId())));
            jsonObject1.put("unitName", productUnit.getUnit().getName());
            jsonObject1.put("unitPrice", productUnit.getUnitPrice());
            units.add(jsonObject1);
        });
        jsonObject.put("units", units);
        return jsonObject;
    }

    @Override
    @Transactional
    public List<JSONObject> getProductNames(String productName, String... params) {
        List<Product> products = productRepository.getProducts(productName, params);
        List<JSONObject> jsonObjects = new ArrayList<>();
        products.forEach(e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productName", e.getName());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }

    @Override
    @Transactional
    public List<JSONObject> getProductDetails(String productName, int offset, int limit, String... params) {
        List<Product> products = productRepository.getProducts(productName,offset, limit, params);
        List<JSONObject> jsonObjects = new ArrayList<>();
        products.forEach(e->{
            //product
            JSONObject productJsonObject = new JSONObject();
            productJsonObject.put("productId", UtilsController.encodeBase64(String.valueOf(e.getProductId())));
            productJsonObject.put("productName", e.getName());
            productJsonObject.put("productImage", e.getImage());

            //category
            JSONObject cateJsonObject = new JSONObject();
            cateJsonObject.put("categoryName", e.getCategory().getName());

            //stall
            Stall stall =  stallRepository.getStall(e.getStall().getStallId(), "location", "user");
            JSONObject stallJsonObject = new JSONObject();
            stallJsonObject.put("stallName", stall.getName());
            stallJsonObject.put("stallImage", stall.getImage());
            stallJsonObject.put("stallDescription",stall.getDescription());

            //location
            Location location = stall.getLocation();
            JSONObject locaJsonObject = new JSONObject();
            locaJsonObject.put("locationName", location.getName());
            locaJsonObject.put("locationLongitude", location.getLongitude());
            locaJsonObject.put("locationLatitude", location.getLatitude());

            //user
            User user = userRepository.getUser(stall.getUser().getUserId(), "phoneNumbers");
            JSONObject userJsonObject = new JSONObject();
            userJsonObject.put("userFirstName", user.getFirstName());
            userJsonObject.put("userLastName", user.getLastName());
            userJsonObject.put("userFacebookLink", user.getFacebookLink());
            List<JSONObject> phoneNumbers = new ArrayList<>();
            user.getPhoneNumbers().forEach(e1->{
                JSONObject phoneNumber= new JSONObject();
                phoneNumber.put("name", e1.getPhoneNumber());
                phoneNumbers.add(phoneNumber);
            });
            userJsonObject.put("phoneNumbers", phoneNumbers);

            //units
            List<JSONObject> units = new ArrayList<>();
            e.getProductUnits().forEach(e1->{
                ProductUnit productUnit = productUnitRepository.getProductUnit(
                        e1.getProductUnitId(), "unit");
                JSONObject unit = new JSONObject();
                unit.put("unitName", productUnit.getUnit().getName());
                unit.put("unitPrice", productUnit.getUnitPrice());
                units.add(unit);
            });

            // product detail
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product", productJsonObject);
            jsonObject.put("category", cateJsonObject);
            jsonObject.put("stall", stallJsonObject);
            jsonObject.put("location", locaJsonObject);
            jsonObject.put("user", userJsonObject);
            jsonObject.put("units", units);
            jsonObjects.add(jsonObject);

        });
        return jsonObjects;
    }

    @Override
    @Transactional
    public JSONObject getAmountProductDetails(String productName) {
        JSONObject jsonObject = new JSONObject();
        List<Product> products= productRepository.getProducts(productName);
        jsonObject.put("amount", products==null?0:products.size());
        return jsonObject;
    }
}
