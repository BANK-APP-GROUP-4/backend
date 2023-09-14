package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.SavingsAccount;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private SavingsAccountService savingsAccountService;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public void addTransaction(Long senderAccNumber, Long receiverAccNumber, Long amount){
        Optional<SavingsAccount> senderAccOp = savingsAccountService.findSavingsAccountById(senderAccNumber);
        Optional<SavingsAccount> receiverAccOp = savingsAccountService.findSavingsAccountById(receiverAccNumber);
        if(receiverAccOp.isPresent()){
            SavingsAccount senderAcc =  senderAccOp.get();
            SavingsAccount receiverAcc =  receiverAccOp.get();
            Long balance = senderAcc.getBalance();
            if(balance - amount >= SavingsAccount.minBalance){
                senderAcc.setBalance(balance-amount);
                receiverAcc.setBalance(receiverAcc.getBalance()+amount);
                transactionRepo.save(
                        new Transaction(
                                amount,
                                LocalDate.now(),
                                senderAcc,
                                receiverAcc
                        )
                );
                System.out.println("Transaction Successful.");
            }
            else{
                System.out.println("Not enough balance.");
            }
        }
        else {
            System.out.println("User not found.");
        }
    }
}