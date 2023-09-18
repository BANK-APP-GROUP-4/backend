package com.wellsfargo.bankapp.entity.account;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="savings_account")
public class SavingsAccount {
    public static double interestRate = 0.02;
    public static Long minBalance = 5000L;
    public static double monthlyMaintenanceFee = 100;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @Column(name="activation_date")
    private LocalDate activationDate;
    @Column(name="balance")
    private double balance;
    @OneToMany(mappedBy="receiverAcc")
    private List<Transaction> creditTransactions;

    @OneToMany(mappedBy="senderAcc")
    private List<Transaction> debitTransactions;

    public SavingsAccount() {}

    public SavingsAccount(Customer customer, LocalDate activationDate, double balance) {
        this.customer = customer;
        this.activationDate = activationDate;
        this.balance = balance;
    }

    public SavingsAccount(
            Customer customer,
            LocalDate activationDate,
            double balance,
            List<Transaction> creditTransactions,
            List<Transaction> debitTransactions
    ) {
        this.customer = customer;
        this.activationDate = activationDate;
        this.balance = balance;
        this.creditTransactions = creditTransactions;
        this.debitTransactions = debitTransactions;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public List<Transaction> getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(List<Transaction> creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

    public List<Transaction> getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(List<Transaction> debitTransactions) {
        this.debitTransactions = debitTransactions;
    }
}
