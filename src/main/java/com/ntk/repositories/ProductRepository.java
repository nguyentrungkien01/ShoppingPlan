package com.ntk.repositories;

import com.ntk.pojos.Product;

import java.util.List;

public interface ProductRepository {
    boolean addProduct(Product product);
    boolean deleteProduct(Product product);
    boolean updateProduct(Product product);
    Product getProduct(int productId, String... params);
    List<Product> getProducts(String productName, String... params);
    List<Product> getProducts(String productName, int offset, int limit, String... params);
}
