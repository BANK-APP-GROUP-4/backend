package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.exception.SavingsAccountNotFoundException;
import com.wellsfargo.bankapp.mapper.SavingsAccountDTOMapper;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {
    private SavingsAccountRepo savingsAccountRepo;
    private final SavingsAccountDTOMapper savingsAccountDTOMapper;
    private final CustomerService customerService;
    @Autowired
    public SavingsAccountService(
            SavingsAccountRepo savingsAccountRepo,
            SavingsAccountDTOMapper savingsAccountDTOMapper,
            CustomerService customerService
    ) {
        this.savingsAccountRepo = savingsAccountRepo;
        this.savingsAccountDTOMapper = savingsAccountDTOMapper;
        this.customerService = customerService;
    }

    public void  createSavingsAccount(
            Customer customer,
            double depositAmount,
            Boolean hasCreditCard,
            Boolean hasDebitCard
    ) throws Exception {
        //Customer customer = customerService.findCustomerByIdInternal(customerId);
        if(depositAmount >= SavingsAccount.minBalance){
            savingsAccountRepo.save(new SavingsAccount(
                    customer, LocalDate.now(), LocalDate.now(), depositAmount, hasCreditCard, hasDebitCard, false
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
    
    public List<SavingsAccount> findSavingsAccountByCustId(Long cust_id) {
    	List<SavingsAccount> list = savingsAccountRepo.findByCustId(cust_id);
    	for(SavingsAccount s1:list) {
    		s1.setAccountStatus(true);
    	}
        return savingsAccountRepo.findByCustId(cust_id);
    }

    public SavingsAccount findSavingsAccountByIdInternal(Long id) {
        return savingsAccountRepo.findById(id)
                .orElseThrow(() -> new SavingsAccountNotFoundException("Savings account by id " + id + " was not found."));
    }

    //@Scheduled(cron="0 0 2 * * ?")
    @Scheduled(fixedDelay=1000)
    public void MonthlyOperation() {
        List<SavingsAccount> accounts = savingsAccountRepo.findAll();

        for (SavingsAccount account : accounts) {
            if (isEligible(account) && account.getAccountStatus() == true) {
                double interest = calculateInterest(account);
                SavingsAccount accountAfterUpdate = updateAccountBalance(account, interest);
                savingsAccountRepo.save(accountAfterUpdate);
            }
        }
    }

    // Helper methods for calculating and updating interest
    private boolean isEligible(SavingsAccount savingsAccount) {
        // Add logic to determine if the account is eligible for interest calculation
        // For example, check if the activation date is before today
        Boolean isEligible = savingsAccount.getLastUpdateDate().plusMonths(1).isAfter(LocalDate.now());
        savingsAccount.setLastUpdateDate(LocalDate.now());
        return isEligible;
    }

    private double calculateInterest(SavingsAccount savingsAccount) {
        // Add your interest calculation logic here
        // Example logic:
        // Calculate interest based on the balance, interest rate, and time since activation
        return SavingsAccount.interestRate * savingsAccount.getBalance(); // Assuming a fixed interest rate of 3%
    }

    public SavingsAccount updateAccountBalance(SavingsAccount account, double amount) {
        // Update the account balance with the calculated interest
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        savingsAccountRepo.save(account);
        return account;
    } 
}
