package com.ntk.services.Impl;

import com.ntk.pojos.Account;
import com.ntk.pojos.AccountReport;
import com.ntk.pojos.AccountReportId;
import com.ntk.repositories.AccountReportRepository;
import com.ntk.services.AccountReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountReportServiceImpl implements AccountReportService {

    @Autowired
    private AccountReportRepository accountReportRepository;

    @Override
    @Transactional
    public boolean addAccountReport(AccountReport accountReport) {
        accountReport.setAmount(1);
        return accountReportRepository.addAccountReport(accountReport);
    }

    @Override
    @Transactional
    public boolean updateAccountReport(AccountReport accountReport) {
        return accountReportRepository.updateAccountReport(accountReport);
    }

    @Override
    @Transactional
    public int countAccountReport(Account account) {
        return accountReportRepository.countAccountReport(account);
    }

    @Override
    @Transactional
    public AccountReport getAccountReport(AccountReportId accountReportId) {
        return accountReportRepository.getAccountReport(accountReportId);
    }
}
