package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void addTransaction(Long senderAccId, Long receiverAccId, double amount) throws Exception {
        Optional<SavingsAccount> senderAccOp = savingsAccountService.findSavingsAccountById(senderAccId);
        Optional<SavingsAccount> receiverAccOp = savingsAccountService.findSavingsAccountById(receiverAccId);
        if(receiverAccOp.isPresent()){
            SavingsAccount senderAcc =  senderAccOp.get();
            SavingsAccount receiverAcc =  receiverAccOp.get();
            double balance = senderAcc.getBalance();
            String status;

            if(balance - amount >= SavingsAccount.minBalance){
                senderAcc.setBalance(balance-amount);
                receiverAcc.setBalance(receiverAcc.getBalance()+amount);
                status = "VALID";

            }
            else{

                status = "INVALID";
                throw new Exception("Balance not enough.");
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
            throw new Exception("User not found.");
        }
    }
}