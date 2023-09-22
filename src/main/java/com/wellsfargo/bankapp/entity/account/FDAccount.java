package com.wellsfargo.bankapp.entity.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wellsfargo.bankapp.entity.Customer;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="fd_account")
public class FDAccount {

    public static double minPrincipalAmount = 5000;
    public static double interestRate = 0.071;
    public static double penaltyForEarlyWithdrawal = 0.01;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="activation_date")
    private LocalDate activationDate;
    @ManyToOne
	@JsonBackReference
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column(name="principal_amount")
    private double principalAmount;
    @Column(name="maturity_period")
    private int maturityPeriod;

    public FDAccount(){}

    public FDAccount(LocalDate activationDate, Customer customer, double principalAmount, int maturityPeriod) {
        this.activationDate = activationDate;
        this.customer = customer;
        this.principalAmount = principalAmount;
        this.maturityPeriod = maturityPeriod;
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
}