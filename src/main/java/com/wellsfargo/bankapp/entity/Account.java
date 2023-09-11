package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
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
}

