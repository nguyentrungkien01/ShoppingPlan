package com.ntk.repositories.impl;

import com.ntk.pojos.UnitType;
import com.ntk.repositories.UnitTypeRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class UnitTypeRepositoryImpl implements UnitTypeRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    private void fetchUnitType(Root<UnitType> root, String...params){
        if(params!=null && params.length>0)
            Arrays.stream(params).forEach(e->{
                if(Objects.equals(e, "units"))
                    root.fetch(e, JoinType.LEFT);
            });
    }

    @Override
    public UnitType getUnitType(int unitTypeId, String... params) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<UnitType> criteriaQuery = criteriaBuilder.createQuery(UnitType.class);
        Root<UnitType> root = criteriaQuery.from(UnitType.class);
        fetchUnitType(root, params);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("unitTypeId").as(Integer.class),
                unitTypeId);
        List<UnitType> unitTypes = s.createQuery(criteriaQuery.where(p)).getResultList();
        if (unitTypes.isEmpty())
            return null;
        return unitTypes.get(0);
    }

    @Override
    public List<UnitType> getUnitTypes() {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<UnitType> criteriaQuery = criteriaBuilder.createQuery(UnitType.class);
        Root<UnitType> root = criteriaQuery.from(UnitType.class);
        criteriaQuery.select(root);
        return s.createQuery(criteriaQuery).getResultList();
    }
}
