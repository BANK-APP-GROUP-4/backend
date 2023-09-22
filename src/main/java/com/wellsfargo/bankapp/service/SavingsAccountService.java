package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public void  createSavingsAccount(Long customerId, double depositAmount) throws Exception {
        Optional<Customer> customer = customerService.findCustomerById(customerId);
        if(!customer.isPresent()){
            throw new Exception("Customer not present.");
        }
        if(depositAmount >= SavingsAccount.minBalance){
            savingsAccountRepo.save(
                    new SavingsAccount(
                            customer.get(),
                            LocalDate.now(),
                            depositAmount
                    )
            );
        }
        else{
            throw new Exception("Deposit amount has to be more than minimum balance.");
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
        return savingsAccount.getActivationDate().isBefore(LocalDate.now());
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
    
    public List<Transaction> getTransactionHistory(Long id){
    	Optional<SavingsAccount> savingsAccountOp = findSavingsAccountById(id);
    	List<Transaction> history = new ArrayList<>();
        if(savingsAccountOp.isPresent()){
            SavingsAccount savingsAccount = savingsAccountOp.get();
            List<Transaction> credTransactions = savingsAccount.getCreditTransactions();
            List<Transaction> debTransactions = savingsAccount.getDebitTransactions();
            Collections.reverse(debTransactions);
            Collections.reverse(credTransactions);

            
            Integer i1=0;
            Integer i2=0;
            
            while(i1<credTransactions.size() && i2<debTransactions.size()) {
            	LocalDateTime dateTime1=credTransactions.get(i1).getDateOfTransaction();
    			LocalDateTime dateTime2=debTransactions.get(i2).getDateOfTransaction();
    			if(dateTime1.isAfter(dateTime2)) {
    				history.add(credTransactions.get(i1));
    				i1=i1+1;
    				
    			}else {
    				Transaction temp=debTransactions.get(i2);
            		
            		temp.setAmount((temp.getAmount())*(-1));
            		history.add(temp);
    				//history.add(debTransactions.get(i2));
    				i2=i2+1;
    				
    			}
            }
            if(i1<credTransactions.size()) {
            	for(int i=i1;i<credTransactions.size();i++) {
            		history.add(credTransactions.get(i));
            	}
            }
            if(i2<debTransactions.size()) {
            	for(int i=i2;i<debTransactions.size();i++) {
            		Transaction temp=debTransactions.get(i);
            		
            		temp.setAmount((temp.getAmount())*(-1));
            		history.add(temp);
            	}
            }

            
    }
        return history;
    }
}
