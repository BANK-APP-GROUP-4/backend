package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.exception.CustomerNotFoundException;
import com.wellsfargo.bankapp.exception.SavingsAccountNotFoundException;
import com.wellsfargo.bankapp.mapper.SavingsAccountDTOMapper;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {

    private SavingsAccountRepo savingsAccountRepo;
    private final SavingsAccountDTOMapper savingsAccountDTOMapper;
    private final CustomerService customerService;
    @Autowired
    public SavingsAccountService(SavingsAccountRepo savingsAccountRepo, SavingsAccountDTOMapper savingsAccountDTOMapper, CustomerService customerService) {
        this.savingsAccountRepo = savingsAccountRepo;
        this.savingsAccountDTOMapper = savingsAccountDTOMapper;
        this.customerService = customerService;
    }

    public void  createSavingsAccount(Long customerId, double depositAmount, Boolean hasCreditCard, Boolean hasDebitCard) throws Exception {
        Customer customer = customerService.findCustomerByIdInternal(customerId);
        if(depositAmount >= SavingsAccount.minBalance){
            savingsAccountRepo.save(new SavingsAccount(
                    customer, LocalDateTime.now(), depositAmount, hasCreditCard, hasDebitCard
                )
            );
        }
        else{
            throw new Exception("Deposit amount has to be more than minimum balance.");
        }
    }
    
    public SavingsAccountDTO findSavingsAccountById(Long id) {
        return savingsAccountRepo.findById(id)
                .map(savingsAccountDTOMapper)
                .orElseThrow(() -> new SavingsAccountNotFoundException("Savings account by id " + id + " was not found."));
    }

    public SavingsAccount findSavingsAccountByIdInternal(Long id) {
        return savingsAccountRepo.findById(id)
                .orElseThrow(() -> new SavingsAccountNotFoundException("Savings account by id " + id + " was not found."));
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
