package com.ntk.services.Impl;

import com.ntk.pojos.StallProduct;
import com.ntk.repositories.StallProductRepository;
import com.ntk.services.StallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StallProductServiceImpl implements StallProductService {
    @Autowired
    private StallProductRepository stallProductRepository;

    @Override
    @Transactional
    public boolean deleteStallProduct(StallProduct stallProduct) {
        return stallProductRepository.deleteStallProduct(stallProduct);
    }
}
