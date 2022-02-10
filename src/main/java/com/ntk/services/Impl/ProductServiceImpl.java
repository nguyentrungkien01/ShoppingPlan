package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Product;
import com.ntk.pojos.ProductUnit;
import com.ntk.repositories.ProductRepository;
import com.ntk.repositories.ProductUnitRepository;
import com.ntk.repositories.StallProductRepository;
import com.ntk.repositories.UserProductRepository;
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
    private StallProductRepository stallProductRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    @Transactional
    public boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    @Transactional
    public boolean deleteProduct(int productId) {
        Product product = productRepository.getProduct(productId, "userProducts","stallProducts","productUnits");
        if(product==null)
            return false;
        product.getStallProducts().forEach(e->stallProductRepository.deleteStallProduct(e));
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
    public List<JSONObject> getProducts(String productName, String... params) {
        List<Product> products = productRepository.getProducts(productName, params);
        List<JSONObject> jsonObjects = new ArrayList<>();
        products.forEach(e->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productId",
                    UtilsController.encodeBase64(String.valueOf(e.getProductId())));
            jsonObject.put("productName", e.getName());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }
}
