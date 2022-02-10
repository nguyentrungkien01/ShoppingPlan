package com.ntk.services;

import com.ntk.pojos.StallProduct;
import com.ntk.pojos.StallProductId;

public interface StallProductService {
    boolean addStallProduct(StallProduct stallProduct);
    boolean deleteStallProduct(StallProduct stallProduct);
    StallProduct getStallProduct(StallProductId stallProductId, String... params);
}
