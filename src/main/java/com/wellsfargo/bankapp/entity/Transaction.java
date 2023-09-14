package com.wellsfargo.bankapp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	@Column(name="amount")
	private Long amount;
	@Column(name="date_of_transaction")
	private LocalDate dateOfTransaction;
	@ManyToOne
	@JoinColumn(name="sender_account_id")
	private SavingsAccount senderAcc;

	@ManyToOne
	@JoinColumn(name="receiver_account_id")
	private SavingsAccount receiverAcc;

	public Transaction(Long amount, LocalDate dateOfTransaction, SavingsAccount senderAcc, SavingsAccount receiverAcc) {
		this.amount = amount;
		this.dateOfTransaction = dateOfTransaction;
		this.senderAcc = senderAcc;
		this.receiverAcc = receiverAcc;
	}

	public Long getAmount() {
		return amount;
	}

	public SavingsAccount getSenderAcc() {
		return senderAcc;
	}

	public SavingsAccount getReceiverAcc() {
		return receiverAcc;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public void setSenderAcc(SavingsAccount senderAcc) {
		this.senderAcc = senderAcc;
	}

	public void setReceiverAcc(SavingsAccount receiverAcc) {
		this.receiverAcc = receiverAcc;
	}

	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
}
