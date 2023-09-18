package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.FDAccount;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FDAccountService {
    @Autowired
    private FDAccountRepo fdAccountRepo;

    @Autowired
    private CustomerService customerService;

    public FDAccountService(FDAccountRepo fdAccountRepo) {
        this.fdAccountRepo = fdAccountRepo;
    }

    public void  createFDAccount(Long customerId, double principalAmount, int maturityPeriod) throws Exception {
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        if(!customer.isPresent()){
            throw new Exception("Customer not present.");
        }
        if(principalAmount >= FDAccount.minPrincipalAmount){
            fdAccountRepo.save(
                    new FDAccount(
                            LocalDate.now(),
                            customer.get(),
                            principalAmount,
                            maturityPeriod
                    )
            );
        }
        else{
            throw new Exception("Principal amount has to be more than minimum balance.");
        }
    }
}
