package com.ntk.repositories.impl;

import com.ntk.pojos.User;
import com.ntk.pojos.UserProduct;
import com.ntk.pojos.UserProductId;
import com.ntk.repositories.UserProductRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class UserProductRepositoryImpl implements UserProductRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public boolean deleteUserProduct(UserProduct userProduct) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(userProduct);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    @Override
    public boolean addUserProduct(UserProduct userProduct) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(userProduct);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
    private void fetchUserProduct(Root<UserProduct> root, String... params){
        if (params != null && params.length > 0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "product") ||
                        Objects.equals(e, "user"))
                root.fetch(e);
            });
    }

    @Override
    public List<UserProduct> getUserProducts(UserProductId userProductId, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<UserProduct> criteriaQuery= criteriaBuilder.createQuery(UserProduct.class);
        Root<UserProduct> root = criteriaQuery.from(UserProduct.class);
        fetchUserProduct(root, params);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("userProductId").as(UserProductId.class), userProductId);
        return s.createQuery(criteriaQuery.where(p)).getResultList();
    }

    @Override
    public List<UserProduct> getUserProducts(User user, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<UserProduct> criteriaQuery= criteriaBuilder.createQuery(UserProduct.class);
        Root<UserProduct> root = criteriaQuery.from(UserProduct.class);
        fetchUserProduct(root, params);
        Predicate p = criteriaBuilder.equal(root.get("user").as(User.class), user);
        return s.createQuery(criteriaQuery.where(p)).getResultList();
    }
}
