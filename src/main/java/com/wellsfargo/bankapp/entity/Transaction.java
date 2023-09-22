package com.wellsfargo.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	@Column(name="amount")
	private double amount;
	@Column(name="date_of_transaction")
	private LocalDateTime dateOfTransaction;

	@Column(name="transaction_status")
	private String status;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="sender_account_id")
	private SavingsAccount senderAcc;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="receiver_account_id")
	private SavingsAccount receiverAcc;

	public Transaction() {}

	public Transaction(SavingsAccount senderAcc, SavingsAccount receiverAcc, double amount, LocalDateTime dateOfTransaction, String status) {
		this.senderAcc = senderAcc;
		this.receiverAcc = receiverAcc;
		this.amount = amount;
		this.dateOfTransaction = dateOfTransaction;
		this.status = status;
	}

	public double getAmount()  {
		return amount;
	}

	public SavingsAccount getSenderAcc() {
		return senderAcc;
	}

	public SavingsAccount getReceiverAcc() {
		return receiverAcc;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSenderAcc(SavingsAccount senderAcc) {
		this.senderAcc = senderAcc;
	}

	public void setReceiverAcc(SavingsAccount receiverAcc) {
		this.receiverAcc = receiverAcc;
	}

	public LocalDateTime getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
