package com.wellsfargo.bankapp.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    
    private String accountNumber;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;
   
    
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
    
    
	
	
    public Account(String accountNumber, String accounttype, int balance, String userid, String branch, LocalDate activationdate, LocalDate deactivationdate) {
        this.accountNumber = accountNumber;
        this.accounttype = accounttype;
        this.balance = balance;
        this.userid = userid;
        this.branch = branch;
        this.activationdate = activationdate;
        this.deactivationdate = deactivationdate;
    }
    
    public Long getAId() {
        return aid;
    }
    public void setAId(Long aid) {
        this.aid = aid;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
    
}

