package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name="savings_account")
public class SavingsAccount extends Account{
    private static double interestRate = 0.02;
    private static Long minBalance = 10000L;
    private static double monthlyMaintenanceFee = 100;
    @Column(name="balance")
    private Long balance;
    @OneToMany(mappedBy="accountDetail", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    private List<Transaction> transactions;

    public SavingsAccount(LocalDate activationDate, Customer customer, Long balance) {
        super(activationDate, customer);
        this.balance = balance;
    }
}
