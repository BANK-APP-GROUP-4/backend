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
	@Column
	private int amount;
	@Column
	private LocalDate date;
	@Column
	private String reciever_acc;
	@ManyToOne(targetEntity=Account.class,cascade=CascadeType.ALL)
	@JoinColumn(name="accountNo")
	private List<Account> accountDetail;

	
	
}
