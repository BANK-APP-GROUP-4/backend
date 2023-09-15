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
//	@ManyToOne(targetEntity=SavingsAccount.class, cascade=CascadeType.ALL)
//	@JoinColumn(name="account_detail")
//	private List<Account> accountDetail;
	
	@Column
	private Long sender_acc;
	
	public Transaction(int amount, LocalDate date, String receiver_acc, List<Account> accountDetail,Long sender_acc) {
		super();
		this.amount = amount;
		this.date = date;
		this.receiver_acc = receiver_acc;
		this.sender_acc = sender_acc;
	}
	
	public Long getSender_acc() {
		return sender_acc;
	}

	public void setSender_acc(Long sender_acc) {
		this.sender_acc = sender_acc;
	}

	public Transaction() {}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getReceiver_acc() {
		return receiver_acc;
	}

	public void setReceiver_acc(String receiver_acc) {
		this.receiver_acc = receiver_acc;
	}

	
	
}
