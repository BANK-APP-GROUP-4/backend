package com.wellsfargo.bankapp.entity.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wellsfargo.bankapp.entity.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="fd_account")
public class FDAccount {
    public static double minPrincipalAmount = 5000;
    public static double interestRate = 0.071;
    @Id
    @GeneratedValue(
            generator="account-id-generator"
    )
    @GenericGenerator(
            name="account-id-generator",
            strategy="com.wellsfargo.bankapp.generator.AccountIdGenerator"
    )
    private Long id;
    @Column(name="activation_date")
    private LocalDate activationDate;
    @Column(name="account_status")
    private Boolean accountStatus;
    @ManyToOne
	@JsonBackReference
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column(name="principal_amount")
    private double principalAmount;
    @Column(name="amount_at_maturity")
    private double amountAtMaturity;
    @Column(name="maturity_period")
    private int maturityPeriod;
    public FDAccount(){}
    public FDAccount(
            Customer customer,
            LocalDate activationDate,
            double principalAmount,
            int maturityPeriod,
            Boolean accountStatus) {
        this.customer = customer;
        this.activationDate = activationDate;
        this.principalAmount = principalAmount;
        this.maturityPeriod = maturityPeriod;
        this.accountStatus = accountStatus;
        this.amountAtMaturity = principalAmount + (principalAmount * interestRate * maturityPeriod);
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

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public int getMaturityPeriod() {
        return maturityPeriod;
    }

    public void setMaturityPeriod(int maturityPeriod) {
        this.maturityPeriod = maturityPeriod;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public double getAmountAtMaturity() {
        return amountAtMaturity;
    }

    public void setAmountAtMaturity(double amountAtMaturity) {
        this.amountAtMaturity = amountAtMaturity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}