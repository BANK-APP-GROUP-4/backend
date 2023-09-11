package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.FDAccount;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FDAccountService {
    @Autowired
    private FDAccountRepo fdAccountRepo;

    public FDAccountService(FDAccountRepo fdAccountRepo) {
        this.fdAccountRepo = fdAccountRepo;
    }

    void addFDAccount(FDAccount fdAccount){
        fdAccountRepo.save(fdAccount);
    }
}
