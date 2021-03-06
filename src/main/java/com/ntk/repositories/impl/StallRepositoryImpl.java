package com.ntk.repositories.impl;


import com.ntk.pojos.*;
import com.ntk.repositories.StallRepository;
import com.ntk.services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class StallRepositoryImpl implements StallRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private UserService userService;

    @Override
    public List<Stall> getStallInfo(int... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Stall> query = criteriaBuilder.createQuery(Stall.class);
        Root<Stall> root = query.from(Stall.class);
        query.select(root);
        Predicate p = criteriaBuilder.equal(root.get("user").as(User.class),
                userService.getCurrentUser());
        query = query.where(p);
        if (params.length == 2 && params[0] != -1 && params[1] != -1)
            return s.createQuery(query).setFirstResult(params[0]).setMaxResults(params[1]).getResultList();

        return s.createQuery(query).getResultList();
    }

    @Override
    public int getStallAmount() {
        return getStallInfo().toArray().length;
    }

    @Override
    public Location getStallLocation(int stallId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Stall> query = criteriaBuilder.createQuery(Stall.class);
        Root<Stall> root = query.from(Stall.class);
        query.select(root);
        Predicate predicate = criteriaBuilder.equal(
                root.get("stallId").as(Integer.class), stallId);
        List<Stall> locations = s.createQuery(query.where(predicate)).getResultList();
        if (locations.isEmpty())
            return null;
        return locations.get(0).getLocation();
    }

    @Override
    public List<Product> getProductList(int stallId, int... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();

        //get stall
        CriteriaQuery<Stall> stallCriteriaQuery = criteriaBuilder.createQuery(Stall.class);
        Root<Stall> stallRoot = stallCriteriaQuery.from(Stall.class);
        stallCriteriaQuery.select(stallRoot);
        Predicate stallPredicate = criteriaBuilder.equal(
                stallRoot.get("stallId").as(Stall.class), stallId);
        Stall stall = s.createQuery(stallCriteriaQuery.where(stallPredicate)).getResultList().get(0);

        //get stall product
        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        productCriteriaQuery.select(productRoot);
        Predicate predicate = criteriaBuilder.equal(productRoot.get("stall").as(Stall.class), stall);
        productCriteriaQuery = productCriteriaQuery.where(predicate);

        //get product list
        if (params.length == 2 && params[0] != -1 && params[1] != -1)
            return s.createQuery(productCriteriaQuery).setFirstResult(params[0]).setMaxResults(params[1])
                    .getResultList();
        return s.createQuery(productCriteriaQuery).getResultList();
    }

    @Override
    public List<ProductUnit> getProductUnitList(int productId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        Predicate predicate = criteriaBuilder.equal(
                root.get("productId").as(Integer.class), productId);
        List<Product> products = s.createQuery(query.where(predicate)).getResultList();
        if (products.isEmpty())
            return null;
        return products.get(0).getProductUnits().stream().toList();
    }

    @Override
    public String getStallName(int stallId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Stall> query = criteriaBuilder.createQuery(Stall.class);
        Root<Stall> root = query.from(Stall.class);
        query.select(root);
        Predicate predicate = criteriaBuilder.equal(
                root.get("stallId").as(Integer.class), stallId);
        List<Stall> stalls = s.createQuery(query.where(predicate)).getResultList();
        if (stalls.isEmpty())
            return null;
        return stalls.get(0).getName();
    }

    @Override
    public int getStallProductListAmount(int stallId) {
        return getProductList(stallId).toArray().length;
    }

    @Override
    public boolean addStall(Stall stall) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(stall);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateStall(Stall stall) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(stall);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteStall(Stall stall) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(stall);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    private void fetchStall(Root<Stall> root, String...params) {
        if (params != null && params.length > 0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "products"))
                    root.fetch(e, JoinType.LEFT);
                if (Objects.equals(e, "location") || Objects.equals(e, "user"))
                    root.fetch(e);
            });
    }
    @Override
    public Stall getStall(int stallId, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Stall> criteriaQuery = criteriaBuilder.createQuery(Stall.class);
        Root<Stall> root = criteriaQuery.from(Stall.class);
        fetchStall(root,params);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("stallId").as(Integer.class), stallId);
        List<Stall> stalls = s.createQuery(criteriaQuery.where(p)).getResultList();
        if (stalls.isEmpty())
            return null;
        return stalls.get(0);
    }


}
