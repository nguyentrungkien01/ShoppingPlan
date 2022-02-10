package com.ntk.repositories;

import com.ntk.pojos.ProductUnit;
import com.ntk.pojos.ProductUnitId;

public interface ProductUnitRepository {
    boolean addProductUnit(ProductUnit productUnit);
    ProductUnit getProductUnit(ProductUnitId productUnitId, String... params);
    boolean deleteProductUnit(ProductUnit productUnit);
}
