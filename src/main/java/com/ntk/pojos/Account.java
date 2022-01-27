package com.ntk.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "username",unique = true, length = 10, nullable = false)
    private String username;

    @Column(name="password", length = 40, nullable = false)
    private String password;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "is_active")
    private byte isActive;

    @Column(name= "amount_report")
    private int amountReport;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountReport> accountReports;

    @OneToOne(mappedBy ="account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public int getAmountReport() {
        return amountReport;
    }

    public void setAmountReport(int amountReport) {
        this.amountReport = amountReport;
    }

    public Set<AccountReport> getAccountReports() {
        return accountReports;
    }

    public void setAccountReports(Set<AccountReport> accountReports) {
        this.accountReports = accountReports;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
