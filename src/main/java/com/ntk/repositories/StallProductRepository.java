package com.ntk.repositories;

import com.ntk.pojos.StallProduct;
import com.ntk.pojos.StallProductId;

public interface StallProductRepository {
    boolean addStallProduct(StallProduct stallProduct);
    boolean deleteStallProduct(StallProduct stallProduct);
    StallProduct getStallProduct(StallProductId stallProductId, String...params);
}
