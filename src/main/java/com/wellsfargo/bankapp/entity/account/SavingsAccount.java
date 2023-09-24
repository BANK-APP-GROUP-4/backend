package com.wellsfargo.bankapp.entity.account;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="savings_account")
public class SavingsAccount {
    public static double interestRate = 0.02;
    public static Long minBalance = 5000L;
    public static double monthlyMaintenanceFee = 100;

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
    private Customer customer;
    @Column(name="activation_date")
    private LocalDateTime activationDate;
    public Boolean getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name="balance")
    private double balance;

    @Column(name="has_credit_card")
    private Boolean hasCreditCard;
    @Column(name="has_debit_card")
    private Boolean hasDebitCard;
    @Column(name="accountStatus")
    private Boolean accountStatus;
    
    
    public SavingsAccount() {}
    public SavingsAccount(
            Customer customer,
            LocalDateTime activationDate,
            double balance,
            Boolean hasCreditCard,
            Boolean hasDebitCard
            
    ) {
        this.customer = customer;
        this.activationDate = activationDate;
        this.balance = balance;
        this.hasCreditCard = hasCreditCard;
        this.hasDebitCard = hasDebitCard;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
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
}