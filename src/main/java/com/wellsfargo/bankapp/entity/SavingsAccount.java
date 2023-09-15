package com.wellsfargo.bankapp.entity;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@Entity
@Table(name="savings_account")
public class SavingsAccount extends Account{
    
	
    public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
   
	private static double interestRate = 0.02;
    private static Long minBalance = 10000L;
    private static double monthlyMaintenanceFee = 100;
    @Column(name="balance")
    private Long balance;
//    @OneToMany(mappedBy="accountDetail", fetch= FetchType.EAGER, cascade= CascadeType.ALL)
//    private List<Transaction> transactions;



    public SavingsAccount(LocalDate activationDate, Customer customer, Long balance) {
    	super(activationDate,customer);
    	this.balance = balance;
    }
	public SavingsAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public SavingsAccount(LocalDate dateBecameCustomer, Long accNo, Customer customer, long balance2) {
//		// TODO Auto-generated constructor stub
//	}
    

}
