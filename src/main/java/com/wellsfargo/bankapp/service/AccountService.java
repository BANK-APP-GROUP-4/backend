package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Account;
import com.wellsfargo.bankapp.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    void addAccount(Account account){
        accountRepo.save(account);
    }
}
