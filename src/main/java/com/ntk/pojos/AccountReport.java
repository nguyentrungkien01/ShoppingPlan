package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Account_Report")
public class AccountReport implements Serializable {
    @EmbeddedId
    private AccountReportId accountReportId;

    @Column(name = "report_date")
    private Date reportDate;

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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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
