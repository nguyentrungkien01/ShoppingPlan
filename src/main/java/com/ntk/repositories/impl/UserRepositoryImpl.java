package com.ntk.repositories.impl;

import com.ntk.pojos.User;
import com.ntk.repositories.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public boolean addUser(User user) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(user);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(user);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public User getUser(int userId, String ... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        if(params!=null && params.length>0)
            Arrays.stream(params).forEach(e -> {
                if (Objects.equals(e, "locations") ||
                        Objects.equals(e, "stalls") ||
                        Objects.equals(e, "phoneNumbers"))
                    root.fetch(e);
                if(Objects.equals(e, "userProducts"))
                    root.fetch(e, JoinType.LEFT);
            });
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("userId").as(Integer.class), userId);
        List<User> users = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(users==null || users.toArray().length<=0)
            return null;
        return users.get(0);
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(user);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public User getUser(String idCard) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("idCard").as(String.class), idCard);
        List<User> users = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(users==null || users.toArray().length<=0)
            return null;
        return users.get(0);

    }
}
