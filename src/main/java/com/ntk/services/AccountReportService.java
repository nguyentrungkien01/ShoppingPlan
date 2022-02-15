package com.ntk.services;

import com.ntk.pojos.Account;
import com.ntk.pojos.AccountReport;
import com.ntk.pojos.AccountReportId;

public interface AccountReportService {
    boolean addAccountReport(AccountReport accountReport);
    boolean updateAccountReport(AccountReport accountReport);
    int countAccountReport(Account account);
    AccountReport getAccountReport (AccountReportId accountReportId);
}
