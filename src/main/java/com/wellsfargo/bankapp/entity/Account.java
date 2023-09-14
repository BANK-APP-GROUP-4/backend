package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Account {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="activation_date")
    private LocalDate activationDate;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Account(LocalDate activationDate, Customer customer) {
        this.activationDate = activationDate;
        this.customer = customer;
    }

    public Long getId() {
        return Id;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

