package com.ntk.services.Impl;

import com.ntk.pojos.Product;
import com.ntk.pojos.ProductUnit;
import com.ntk.pojos.ProductUnitId;
import com.ntk.repositories.ProductRepository;
import com.ntk.repositories.ProductUnitRepository;
import com.ntk.services.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ProductUnitServiceImpl implements ProductUnitService{

    @Autowired
    private ProductUnitRepository productUnitRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    @Transactional
    public boolean addProductUnit(ProductUnit productUnit) {
        return productUnitRepository.addProductUnit(productUnit);
    }

    @Override
    @Transactional
    public ProductUnit getProductUnit(ProductUnitId productUnitId, String... params) {
        return productUnitRepository.getProductUnit(productUnitId, params);
    }

    @Override
    @Transactional
    public boolean deleteProductUnit(int productId) {
        Product product = productRepository.getProduct(productId, "productUnits");
        if(product==null)
            return false;
        AtomicBoolean result = new AtomicBoolean(true);
        product.getProductUnits().forEach(e->
                result.set(productUnitRepository.deleteProductUnit(e)));
        return result.get();
    }
}
