package com.ntk.repositories.impl;

import com.ntk.pojos.Category;
import com.ntk.repositories.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public List<Category> getAllCategory() {
        Session s= Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        List<Category> categories = s.createQuery(criteriaQuery).getResultList();
        if(categories.isEmpty())
            return null;
        return categories;
    }

    @Override
    public Category getCategory(int categoryId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery  =criteriaBuilder.createQuery(Category.class);
        Root<Category> root= criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("categoryId").as(Integer.class), categoryId);
        List<Category> categories = s.createQuery(criteriaQuery.where(p)).getResultList();
        if(categories.isEmpty())
            return null;
        return categories.get(0);
    }
}
