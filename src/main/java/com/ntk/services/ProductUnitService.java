package com.ntk.services;

import com.ntk.pojos.ProductUnit;
import com.ntk.pojos.ProductUnitId;

public interface ProductUnitService {
    boolean addProductUnit(ProductUnit productUnit);
    ProductUnit getProductUnit(ProductUnitId productUnitId, String... params);
    boolean deleteProductUnit(int productId);
}
