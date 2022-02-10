package com.ntk.repositories.impl;

import com.ntk.pojos.Product;
import com.ntk.repositories.ProductRepository;
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
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public boolean addProduct(Product product) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(product);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(product);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(product);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public Product getProduct(int productId, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        if (params != null && params.length > 0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "category"))
                    root.fetch("category");
                if (Objects.equals(e, "stallProducts"))
                    root.fetch("stallProducts");
                if (Objects.equals(e, "userProducts"))
                    root.fetch("userProducts");
                if (Objects.equals(e, "productUnits"))
                    root.fetch("productUnits");
            });
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("productId").as(Integer.class), productId);
        List<Product> products = s.createQuery(criteriaQuery.where(p)).getResultList();
        if (products.isEmpty())
            return null;
        return products.get(0);
    }

    @Override
    public List<Product> getProducts(String productName, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        if (params != null && params.length > 0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "category"))
                    root.fetch("category");
                if (Objects.equals(e, "stallProducts"))
                    root.fetch("stallProducts");
                if (Objects.equals(e, "userProducts"))
                    root.fetch("userProducts");
                if (Objects.equals(e, "productUnits"))
                    root.fetch("productUnits");
            });
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.like(root.get("name").as(String.class),
                String.format("%s%%", productName));
        return s.createQuery(criteriaQuery.where(p)
                .orderBy(criteriaBuilder.asc(root.get("name")))).getResultList();
    }
}
