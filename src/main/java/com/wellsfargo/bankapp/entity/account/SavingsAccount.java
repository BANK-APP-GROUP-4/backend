package com.wellsfargo.bankapp.entity.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="savings_account")
public class SavingsAccount {
    public static double interestRate = 0.02;
    public static Long minBalance = 5000L;

    @Id
    @GeneratedValue(
            generator="account-id-generator"
    )
    @GenericGenerator(
            name="account-id-generator",
            strategy="com.wellsfargo.bankapp.generator.AccountIdGenerator"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonBackReference
    private Customer customer;
    @Column(name="activation_date")
    private LocalDate activationDate;
    @Column(name="last_update_date")
    private LocalDate lastUpdateDate;
	@Column(name="balance")
    private double balance;
    @Column(name="has_credit_card")
    private Boolean hasCreditCard;
    @Column(name="has_debit_card")
    private Boolean hasDebitCard;
    @Column(name="account_status")
    private Boolean accountStatus;
    public SavingsAccount() {}
    public SavingsAccount(
            Customer customer,
            LocalDate activationDate,
            LocalDate lastUpdateDate,
            double balance,
            Boolean hasCreditCard,
            Boolean hasDebitCard,
            Boolean accountStatus
            
    ) {
        this.customer = customer;
        this.activationDate = activationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.balance = balance;
        this.hasCreditCard = hasCreditCard;
        this.hasDebitCard = hasDebitCard;
        this.accountStatus = accountStatus;
        
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getActivationDate() {
        return activationDate;
    }
    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Boolean getHasCreditCard() {
        return hasCreditCard;
    }
    public void setHasCreditCard(Boolean hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }
    public Boolean getHasDebitCard() {
        return hasDebitCard;
    }
    public void setHasDebitCard(Boolean hasDebitCard) {
        this.hasDebitCard = hasDebitCard;
    }
    public Boolean getAccountStatus() {
        return accountStatus;
    }
    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }
    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}