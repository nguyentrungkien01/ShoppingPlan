package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Report")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="report_id")
    private int reportId;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "nvarchar")
    private String name;

    @OneToMany(mappedBy = "report")
    private Set<AccountReport> accountReports;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AccountReport> getAccountReports() {
        return accountReports;
    }

    public void setAccountReports(Set<AccountReport> accountReports) {
        this.accountReports = accountReports;
    }

}
