package com.wellsfargo.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@GeneratedValue(
			generator="transaction-id-generator"
	)
	@GenericGenerator(
			name="transaction-id-generator",
			strategy="com.wellsfargo.bankapp.generator.TransactionIdGenerator"
	)
	private Long id;
	@Column(name="amount")
	private double amount;
	@Column(name="date_of_transaction")
	private LocalDate dateOfTransaction;

	@Column(name="transaction_status")
	private String status;
	@ManyToOne
//    @JsonBackReference
	@JoinColumn(name="sender_account_id")
	private SavingsAccount senderAcc;

	@ManyToOne
//    @JsonBackReference
	@JoinColumn(name="receiver_account_id")
	private SavingsAccount receiverAcc;

	public Transaction() {}

	public Transaction(SavingsAccount senderAcc, SavingsAccount receiverAcc, double amount, LocalDate dateOfTransaction, String status) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
