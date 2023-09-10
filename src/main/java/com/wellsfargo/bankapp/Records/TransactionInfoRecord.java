package com.wellsfargo.bankapp.Records;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class TransactionInfoRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tid;
	@Column
	private int amount;
	@Column
	private LocalDate date;
	@Column
	private String reciever_acc;
	@ManyToOne(targetEntity=AccountDetails.class,cascade=CascadeType.ALL)
	@JoinColumn(name="account_transaction",referencedColumnName= "aid")
	private List<AccountDetails> accountdetail;

	public TransactionInfoRecord(int amount, LocalDate date, String reciever_acc) {
		super();
		this.amount = amount;
		this.date = date;
		this.reciever_acc = reciever_acc;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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
	public String getReciever_acc() {
		return reciever_acc;
	}
	public void setReciever_acc(String reciever_acc) {
		this.reciever_acc = reciever_acc;
	}
	
	
	
}
