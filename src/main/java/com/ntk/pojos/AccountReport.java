package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Account_Report")
public class AccountReport implements Serializable {
    @EmbeddedId
    private AccountReportId accountReportId;

    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable=false, updatable=false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "report_id", insertable=false, updatable=false)
    private Report report;

    public AccountReportId getAccountReportId() {
        return accountReportId;
    }

    public void setAccountReportId(AccountReportId accountReportId) {
        this.accountReportId = accountReportId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

}
