package com.wellsfargo.bankapp.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Component;

@Component
public class SavingsAccountDTO {
    private Long id;
    private double balance;
    private Boolean hasCreditCard;
    private Boolean hasDebitCard;

    public SavingsAccountDTO(
            Long id,
            double balance,
            Boolean hasCreditCard,
            Boolean hasDebitCard
    ) {
        this.id = id;
        this.balance = balance;
        this.hasCreditCard = hasCreditCard;
        this.hasDebitCard = hasDebitCard;
    }

    public SavingsAccountDTO() {

    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Boolean getHasCreditCard() {
        return hasCreditCard;
    }

    public Boolean getHasDebitCard() {
        return hasDebitCard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setHasCreditCard(Boolean hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }

    public void setHasDebitCard(Boolean hasDebitCard) {
        this.hasDebitCard = hasDebitCard;
    }
}
