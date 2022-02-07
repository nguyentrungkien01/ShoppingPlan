package com.ntk.repositories.impl;

import com.ntk.pojos.Location;
import com.ntk.pojos.User;
import com.ntk.repositories.LocationRepository;
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
import java.util.Set;

@Repository
public class LocationRepositoryImpl implements LocationRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public boolean addLocation(Location location) {
        try{
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(location);
            return true;
        }
        catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public Location getLocation(int locationId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder= s.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        Root<Location> root = criteriaQuery.from(Location.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("locationId").as(Integer.class), locationId);
        List<Location> locations = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(locations.isEmpty())
            return null;
        return locations.get(0);
    }

}