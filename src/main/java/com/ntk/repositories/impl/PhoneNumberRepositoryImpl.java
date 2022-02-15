package com.ntk.repositories.impl;

import com.ntk.pojos.PhoneNumber;
import com.ntk.repositories.PhoneNumberRepository;
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
public class PhoneNumberRepositoryImpl implements PhoneNumberRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public boolean addPhoneNumber(PhoneNumber phoneNumber) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(phoneNumber);
            return true;
        }catch (Exception e ){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    @Override
    public boolean updatePhoneNumber(PhoneNumber phoneNumber) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(phoneNumber);
            return true;
        }catch (Exception e ){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deletePhoneNumber(PhoneNumber phoneNumber) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(phoneNumber);
            return true;
        }catch (Exception e ){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public PhoneNumber getPhoneNumber(int phoneNumberId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<PhoneNumber> criteriaQuery = criteriaBuilder.createQuery(PhoneNumber.class);
        Root<PhoneNumber> root = criteriaQuery.from(PhoneNumber.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("phoneNumberId").as(Integer.class), phoneNumberId);
        List<PhoneNumber> phoneNumbers = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(phoneNumbers.isEmpty())
            return null;
        return phoneNumbers.get(0);
    }
}
