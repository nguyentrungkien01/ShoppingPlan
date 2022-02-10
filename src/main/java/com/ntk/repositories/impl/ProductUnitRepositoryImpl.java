package com.ntk.repositories.impl;

import com.ntk.pojos.Product;
import com.ntk.pojos.ProductUnit;
import com.ntk.pojos.ProductUnitId;
import com.ntk.repositories.ProductRepository;
import com.ntk.repositories.ProductUnitRepository;
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
public class ProductUnitRepositoryImpl implements ProductUnitRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;


    @Override
    public boolean addProductUnit(ProductUnit productUnit) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(productUnit);
            return true;
        } catch (Exception e) {

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public ProductUnit getProductUnit(ProductUnitId productUnitId, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<ProductUnit> criteriaQuery = criteriaBuilder.createQuery(ProductUnit.class);
        Root<ProductUnit> root = criteriaQuery.from(ProductUnit.class);
        if (params != null && params.length > 0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "product"))
                    root.fetch("product");
                if (Objects.equals(e, "unit"))
                    root.fetch("unit");
            });
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("productUnitId").as(ProductUnitId.class), productUnitId);
        List<ProductUnit> productUnits = s.createQuery(criteriaQuery.where(p)).getResultList();
        if (productUnits.isEmpty())
            return null;
        return productUnits.get(0);
    }

    @Override
    public boolean deleteProductUnit(ProductUnit productUnit) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(productUnit);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
