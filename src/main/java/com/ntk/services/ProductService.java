package com.ntk.services;

import com.ntk.pojos.Product;

public interface ProductService {
    boolean addProduct(Product product);
    boolean deleteProduct(Product product);
    boolean updateProduct(Product product);
    Product getProduct(int productId);
}
