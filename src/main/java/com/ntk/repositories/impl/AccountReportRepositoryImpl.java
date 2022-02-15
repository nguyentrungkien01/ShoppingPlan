package com.ntk.repositories.impl;

import com.ntk.pojos.Account;
import com.ntk.pojos.AccountReport;
import com.ntk.pojos.AccountReportId;
import com.ntk.repositories.AccountReportRepository;
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
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccountReportRepositoryImpl implements AccountReportRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public boolean addAccountReport(AccountReport accountReport) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(accountReport);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateAccountReport(AccountReport accountReport) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(accountReport);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public int countAccountReport(Account account) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<AccountReport> criteriaQuery = criteriaBuilder.createQuery(AccountReport.class);
        Root<AccountReport> root = criteriaQuery.from(AccountReport.class);
        root.fetch("account");
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("account").as(Account.class), account);
        AtomicInteger amount = new AtomicInteger(0);
        s.createQuery(criteriaQuery.where(p)).getResultList().forEach(e -> amount.addAndGet(e.getAmount()));
        return amount.get();
    }

    @Override
    public AccountReport getAccountReport(AccountReportId accountReportId) {
        Session s = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<AccountReport> criteriaQuery = criteriaBuilder.createQuery(AccountReport.class);
        Root<AccountReport> root = criteriaQuery.from(AccountReport.class);
        criteriaQuery.select(root);
        Predicate p = criteriaBuilder.equal(root.get("accountReportId").as(AccountReportId.class), accountReportId);
        List<AccountReport> accountReports = s.createQuery(criteriaQuery.where(p)).getResultList();
        if (accountReports.isEmpty())
            return null;
        return accountReports.get(0);
    }
}
