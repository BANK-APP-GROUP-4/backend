package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name="fd_account")
public class FDAccount extends Account{

    private static double minPrincipalAmount = 5000;
    private static double interestRate = 0.071;
    private static double penaltyForEarlyWithdrawal = 0.01;
    @Column(name="principal_amount")
    private Long principalAmount;
    @Column(name="maturity_period")
    private int maturityPeriod;

    public FDAccount(LocalDate activationDate, Customer customer) {
        super(activationDate, customer);
    }

    public FDAccount(LocalDate activationDate, Customer customer, Long principalAmount, int maturityPeriod) {
        super(activationDate, customer);
        this.principalAmount = principalAmount;
        this.maturityPeriod = maturityPeriod;
    }

    public Long getPrincipalAmount() {
        return principalAmount;
    }

    public int getMaturityPeriod() {
        return maturityPeriod;
    }

    public void setPrincipalAmount(Long principalAmount) {
        this.principalAmount = principalAmount;
    }

    public void setMaturityPeriod(int maturityPeriod) {
        this.maturityPeriod = maturityPeriod;
    }
}