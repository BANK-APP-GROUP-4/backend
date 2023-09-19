package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.entity.Transaction;
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

    public String addTransaction(Long senderAccId, Long receiverAccId, double amount) throws Exception {
        Optional<SavingsAccount> senderAccOp = savingsAccountService.findSavingsAccountById(senderAccId);
        Optional<SavingsAccount> receiverAccOp = savingsAccountService.findSavingsAccountById(receiverAccId);
        String status;
        if(receiverAccOp.isPresent()){
            SavingsAccount senderAcc =  senderAccOp.get();
            SavingsAccount receiverAcc =  receiverAccOp.get();
            double balance = senderAcc.getBalance();

            if(balance - amount >= SavingsAccount.minBalance){
                // transaction successful
                senderAcc.setBalance(balance-amount);
                receiverAcc.setBalance(receiverAcc.getBalance()+amount);
                status = "VALID";

            }
            else{
                // balance not enough
                status = "NO BALANCE";
            }

            transactionRepo.save(
                    new Transaction(
                            senderAcc,
                            receiverAcc,
                            amount,
                            LocalDateTime.now(),
                            status
                    )
            );
        }
        else {
            status = "NO ACCOUNT";
        }
        return status;
    }

    public List<Transaction> getLastKTransactions(Long id, int k) {
        List<Transaction> history = transactionRepo.getLastKTransactions(id);
        return history.stream().limit(k).collect(Collectors.toList());
    }
}