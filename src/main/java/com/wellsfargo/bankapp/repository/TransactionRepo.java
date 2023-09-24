package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.senderAcc.id =  ?1 OR t.receiverAcc.id = ?1 ORDER BY t.dateOfTransaction DESC")
    List<Transaction> getTransactions(Long id);
    @Query("SELECT t FROM Transaction t WHERE (t.senderAcc.id =  ?1 OR t.receiverAcc.id = ?1) AND (t.dateOfTransaction >= ?2)")
    List<Transaction> getStatement(Long id, LocalDateTime oneMonth);
}
