package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.exception.SavingsAccountNotFoundException;
import com.wellsfargo.bankapp.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private SavingsAccountService savingsAccountService;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public void addTransaction(Long senderAccId, Long receiverAccId, double amount) throws Exception {
        if(senderAccId.equals(receiverAccId)){
            throw new Exception("Transferring money between same accounts is not allowed.");
        }
        SavingsAccount senderAcc = savingsAccountService.findSavingsAccountByIdInternal(senderAccId);
        SavingsAccount receiverAcc = savingsAccountService.findSavingsAccountByIdInternal(receiverAccId);
        String status;

        double balance = senderAcc.getBalance();

        if(balance - amount >= SavingsAccount.minBalance){
            // transaction successful
            senderAcc.setBalance(balance-amount);
            receiverAcc.setBalance(receiverAcc.getBalance()+amount);
            status = "VALID";
        }
        else{
            // balance not enough
            status = "INVALID";
        }

        transactionRepo.save(
                new Transaction(
                        senderAcc, receiverAcc, amount, LocalDateTime.now(), status
                )
        );
    }

    public List<Transaction> getLastKTransactions(Long id, int k) {
        savingsAccountService.findSavingsAccountByIdInternal(id);
        List<Transaction> history = transactionRepo.getTransactions(id);
        return history.stream().limit(k).collect(Collectors.toList());
    }

    public List<Transaction> getStatement(Long id, int m) {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(m);
        savingsAccountService.findSavingsAccountByIdInternal(id);
        List<Transaction> list = transactionRepo.getStatement(id, oneMonthAgo);
        return list;
    }
}