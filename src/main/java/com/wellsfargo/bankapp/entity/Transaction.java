package com.wellsfargo.bankapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	@Column(name="amount")
	private int amount;
	@Column(name="date_of_transaction")
	private LocalDate date;
	@Column(name="receiver_acc")
	private String receiver_acc;
	@ManyToOne(targetEntity=SavingsAccount.class, cascade=CascadeType.ALL)
	@JoinColumn(name="account_detail")
	private List<Account> accountDetail;

	
	
}
