package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    void addTransaction(Transaction transaction){
        transactionRepo.save(transaction);
    }
}