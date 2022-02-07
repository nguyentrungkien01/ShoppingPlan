package com.ntk.repositories;

import com.ntk.pojos.Product;

public interface ProductRepository {
    boolean addProduct(Product product);
    boolean deleteProduct(Product product);
    boolean updateProduct(Product product);
    Product getProduct(int productId);
}
