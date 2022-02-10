package com.ntk.repositories.impl;

import com.ntk.pojos.StallProduct;
import com.ntk.pojos.StallProductId;
import com.ntk.repositories.StallProductRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class StallProductRepositoryImpl implements StallProductRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override

    public boolean addStallProduct(StallProduct stallProduct) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(stallProduct);
            return true;
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

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

    @Override
    public StallProduct getStallProduct(StallProductId stallProductId, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<StallProduct> criteriaQuery = criteriaBuilder.createQuery(StallProduct.class);
        Root<StallProduct> root = criteriaQuery.from(StallProduct.class);
        if (params != null && params.length > 0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "stall"))
                    root.fetch("stall");
                if (Objects.equals(e, "product"))
                    root.fetch("product");
            });
        criteriaQuery.select(root);
        Predicate p= criteriaBuilder.equal(root.get("stallProductId").as(StallProductId.class),stallProductId);
        List<StallProduct> stallProducts = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(stallProducts.isEmpty())
            return null;
        return stallProducts.get(0);
    }
}
