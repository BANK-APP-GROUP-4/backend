package com.wellsfargo.bankapp.repository;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	
//public List<Transaction> findBysender_acc(Long sender_acc);
	
	@Query("SELECT c FROM Transaction c WHERE c.sender_acc = :sender_acc")
    List<Transaction> findBysender_acc(@Param("sender_acc") Long sender_acc);
}
