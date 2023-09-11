package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.SavingsAccount;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {
    @Autowired
    private SavingsAccountRepo savingsAccountRepo;

    public SavingsAccountService(SavingsAccountRepo savingsAccountRepo) {
        this.savingsAccountRepo = savingsAccountRepo;
    }

    void addSavingsAccount(SavingsAccount savingsAccount){
        savingsAccountRepo.save(savingsAccount);
    }
}