package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {
    @Autowired
    private SavingsAccountRepo savingsAccountRepo;
    @Autowired
    CustomerService customerService;

    public SavingsAccountService(SavingsAccountRepo savingsAccountRepo) {
        this.savingsAccountRepo = savingsAccountRepo;
    }

    public int  createSavingsAccount(Long customerId, double depositAmount) throws Exception {
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        if(!customer.isPresent()){
            // customer not present
            return 0;
        }
        if(depositAmount >= SavingsAccount.minBalance){
            savingsAccountRepo.save(new SavingsAccount(customer.get(), LocalDateTime.now(), depositAmount));
            return 1;
        }
        else{
            // deposit amount has to be more than minimum balance
            return 2;
        }
    }
    
    public Optional<SavingsAccount> findSavingsAccountById(Long id) {
    	Optional<SavingsAccount> savingsAccountById = savingsAccountRepo.findById(id);
    	return savingsAccountById;
    }

    @Scheduled(cron="0 0 2 * * ?") // Schedule it to run at 2:00 AM daily
    public void calculateAndApplyInterest() {
        List<SavingsAccount> accounts = savingsAccountRepo.findAll();

        for (SavingsAccount account : accounts) {
            if (isEligibleForInterest(account)) {
                double interest = calculateInterest(account);
                updateAccountBalance(account, interest);
            }
        }
    }

    // Helper methods for calculating and updating interest
    private boolean isEligibleForInterest(SavingsAccount savingsAccount) {
        // Add logic to determine if the account is eligible for interest calculation
        // For example, check if the activation date is before today
        return savingsAccount.getActivationDate().isBefore(LocalDateTime.now());
    }

    private double calculateInterest(SavingsAccount savingsAccount) {
        // Add your interest calculation logic here
        // Example logic:
        // Calculate interest based on the balance, interest rate, and time since activation
        return SavingsAccount.interestRate * savingsAccount.getBalance(); // Assuming a fixed interest rate of 3%
    }

    private void updateAccountBalance(SavingsAccount account, double interest) {
        // Update the account balance with the calculated interest
        double newBalance = account.getBalance() + interest;
        account.setBalance(newBalance);
        savingsAccountRepo.save(account);
    }
}
