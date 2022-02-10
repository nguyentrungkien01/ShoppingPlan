package com.ntk.services.Impl;

import com.ntk.pojos.UserProduct;
import com.ntk.repositories.UserProductRepository;
import com.ntk.services.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductServiceImpl implements UserProductService {

    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    public boolean deleteUserProduct(UserProduct userProduct) {
        return userProductRepository.deleteUserProduct(userProduct);
    }
}
