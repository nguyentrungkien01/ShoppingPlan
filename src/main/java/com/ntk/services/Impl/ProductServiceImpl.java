package com.ntk.services.Impl;

import com.ntk.pojos.Product;
import com.ntk.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public boolean addProduct(Product product) {
        return productService.addProduct(product);
    }

    @Override
    @Transactional
    public boolean deleteProduct(Product product) {
        return productService.deleteProduct(product);
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    @Override
    @Transactional
    public Product getProduct(int productId) {
        return productService.getProduct(productId);
    }
}
