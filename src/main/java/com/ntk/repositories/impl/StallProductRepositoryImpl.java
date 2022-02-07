package com.ntk.repositories.impl;

import com.ntk.pojos.StallProduct;
import com.ntk.repositories.StallProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Objects;

@Repository
public class StallProductRepositoryImpl implements StallProductRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;
    @Override
    public boolean deleteStallProduct(StallProduct stallProduct) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(stallProduct);
            return true;
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
