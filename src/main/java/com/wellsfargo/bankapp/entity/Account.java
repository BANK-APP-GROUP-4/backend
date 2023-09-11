package com.wellsfargo.bankapp.entity;

package com.wellsfargo.bankapp.Records;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table
public class Account {
    @Id
    @Column
    private String accountno;
    @Column
    private String accounttype;
    @Column
    private int balance;
    @Column
    private String userid;
    @Column
    private String branch;
    @Column
    private LocalDate activationdate;
    @Column
    private LocalDate deactivationdate;
    public AccountsInfoRecord(String accountno, String accounttype, int balance, String userid, String branch, LocalDate activationdate, LocalDate deactivationdate) {
        this.accountno = accountno;
        this.accounttype = accounttype;
        this.balance = balance;
        this.userid = userid;
        this.branch = branch;
        this.activationdate = activationdate;
        this.deactivationdate = deactivationdate;
    }
    public AccountsInfoRecord() {
    }
    public String getAccountno() {
        return accountno;
    }
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
    public String getAccounttype() {
        return accounttype;
    }
    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public LocalDate getActivationdate() {
        return activationdate;
    }
    public void setActivationdate(LocalDate activationdate) {
        this.activationdate = activationdate;
    }
    public LocalDate getDeactivationdate() {
        return deactivationdate;
    }
    public void setDeactivationdate(LocalDate deactivationdate) {
        this.deactivationdate = deactivationdate;
    }
    @Override
    public String toString() {
        return "AccountsInfoRecord{" +
                "accountno='" + accountno + '\'' +
                ", accounttype='" + accounttype + '\'' +
                ", balance=" + balance +
                ", userid='" + userid + '\'' +
                ", branch='" + branch + '\'' +
                ", activationdate=" + activationdate +
                ", deactivationdate=" + deactivationdate +
                '}';
    }
}

