package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.SavingsAccount;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import java.util.Optional;

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
    
    public SavingsAccount savingsAccount(Long id) {
    	System.out.println(id);
    	SavingsAccount savingsaccount = null;
    	Optional<SavingsAccount> sa = savingsAccountRepo.findById(id);
    	savingsaccount = sa.get();
    	return savingsaccount;
    }
}