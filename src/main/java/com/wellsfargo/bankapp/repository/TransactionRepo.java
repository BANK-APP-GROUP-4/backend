package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
