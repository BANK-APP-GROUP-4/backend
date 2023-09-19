package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.senderAcc =  ?1 OR t.receiverAcc = ?1 ORDER BY t.dateOfTransaction DESC")
    List<Transaction> getLastKTransactions(Long id);
}
