package com.wellsfargo.bankapp.entity.account;

import com.wellsfargo.bankapp.entity.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="fd_account")
public class FDAccount {

    public static double minPrincipalAmount = 5000;
    public static double interestRate = 0.071;
    public static double penaltyForEarlyWithdrawal = 0.01;

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
    private LocalDateTime activationDate;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column(name="principal_amount")
    private double principalAmount;
    @Column(name="maturity_period")
    private int maturityPeriod;
    public FDAccount(){}
    public FDAccount(LocalDateTime activationDate, Customer customer, double principalAmount, int maturityPeriod) {
        this.activationDate = activationDate;
        this.customer = customer;
        this.principalAmount = principalAmount;
        this.maturityPeriod = maturityPeriod;
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
}