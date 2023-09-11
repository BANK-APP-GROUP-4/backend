package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString
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

    public FDAccount(LocalDate activationDate, Customer customer, Long principalAmount, int maturityPeriod) {
        super(activationDate, customer);
        this.principalAmount = principalAmount;
        this.maturityPeriod = maturityPeriod;
    }

}