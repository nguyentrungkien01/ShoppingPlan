package com.ntk.services.Impl;

import com.ntk.pojos.User;
import com.ntk.pojos.UserProduct;
import com.ntk.pojos.UserProductId;
import com.ntk.repositories.UserProductRepository;
import com.ntk.services.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService {

    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    @Transactional
    public boolean deleteUserProduct(UserProduct userProduct) {
        return userProductRepository.deleteUserProduct(userProduct);
    }

    @Override
    @Transactional
    public boolean addUserProduct(UserProduct userProduct) {
        return userProductRepository.addUserProduct(userProduct);
    }

    @Override
    @Transactional
    public List<UserProduct> getUserProducts(UserProductId userProductId, String params) {
        return userProductRepository.getUserProducts(userProductId, params);
    }

    @Override
    @Transactional
    public List<UserProduct> getUserProducts(User user, String... params) {
        return userProductRepository.getUserProducts(user, params);
    }
}
