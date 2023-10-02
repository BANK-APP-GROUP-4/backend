package com.wellsfargo.bankapp.entity;

import javax.persistence.Column;

public class UpdateRequest {
    @Column(nullable=false)
    private long accNumber;

    @Column(nullable=false)
    private long amount;

	public long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
}
