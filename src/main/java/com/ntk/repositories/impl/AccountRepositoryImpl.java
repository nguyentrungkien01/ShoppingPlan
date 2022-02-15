package com.ntk.repositories.impl;

import com.ntk.pojos.Account;
import com.ntk.pojos.Role;
import com.ntk.repositories.AccountRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public Account getAccount(String username) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder= s.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("username").as(String.class), username.trim());
        List<Account> accounts= s.createQuery(criteriaQuery.where(p)).getResultList();
        if(accounts.isEmpty())
            return null;

        return accounts.get(0);
    }

    @Override
    public Account getAccount(int accountId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder= s.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("accountId").as(String.class), accountId);
        List<Account> accounts= s.createQuery(criteriaQuery.where(p)).getResultList();
        if(accounts.isEmpty())
            return null;

        return accounts.get(0);
    }

    @Override
    public boolean addAccount(Account user) {
        try{
            Objects.requireNonNull(localSessionFactoryBean.getObject())
                    .getCurrentSession().save(user);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account user) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject())
                    .getCurrentSession().update(user);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Account user) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject())
                    .getCurrentSession().delete(user);
            return true;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public Role getRole(String name) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root);
        List<Role> roles = s.createQuery(criteriaQuery.where(criteriaBuilder.equal(
                root.get("name").as(String.class), name))).getResultList();
        if(roles.isEmpty())
            return null;
        return roles.get(0);
    }

}
