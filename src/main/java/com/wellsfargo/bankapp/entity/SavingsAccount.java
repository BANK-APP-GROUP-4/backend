package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="savings_account")
public class SavingsAccount extends Account{
    public static double interestRate = 0.02;
    public static Long minBalance = 10000L;
    public static double monthlyMaintenanceFee = 100;
    @Column(name="balance")
    private Long balance;

    @OneToMany(mappedBy="senderAcc")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy="receiverAcc")
    private List<Transaction> receivedTransactions;

    public SavingsAccount(LocalDate activationDate, Customer customer) {
        super(activationDate, customer);
    }

    public SavingsAccount(LocalDate activationDate, Customer customer, Long balance) {
        super(activationDate, customer);
        this.balance = balance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }


}
