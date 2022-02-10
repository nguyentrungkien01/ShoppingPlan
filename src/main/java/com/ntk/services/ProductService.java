package com.ntk.services;

import com.ntk.pojos.Product;
import org.json.simple.JSONObject;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);
    boolean deleteProduct(int productId);
    boolean updateProduct(Product product);
    Product getProduct(int productId);
    JSONObject getProductDetail(int productId, String...params);
    List<JSONObject> getProductNames(String productName, String... params);
    List<JSONObject> getProductDetails(String productName, int offset, int limit, String... params);
}
