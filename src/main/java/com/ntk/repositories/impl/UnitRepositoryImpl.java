package com.ntk.repositories.impl;

import com.ntk.pojos.Unit;
import com.ntk.repositories.UnitRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class UnitRepositoryImpl implements UnitRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public Unit getUnit(int unitId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Unit> criteriaQuery = criteriaBuilder.createQuery(Unit.class);
        Root<Unit> root = criteriaQuery.from(Unit.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("unitId").as(Integer.class), unitId);
        List<Unit> units = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(units.isEmpty())
            return null;
        return units.get(0);
    }
}
