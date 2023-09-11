package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Account;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.SavingsAccount;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private SavingsAccountService savingsAccountService;
    @Autowired
    private FDAccountService fdAccountService;

    public void addCustomer(Customer customer){
        // checking whether the email or mobile number is already taken.
        Optional<Customer> customerByEmail = customerRepo.findCustomerByEmail(customer.getEmail());
        Optional<Customer> customerByMobileNumber = customerRepo.findCustomerByMobileNumber(customer.getMobileNumber());
        if(customerByEmail.isPresent()){
            throw new IllegalStateException("email id taken");
        }
        if(customerByMobileNumber.isPresent()){
            throw new IllegalStateException("mobile number taken");
        }
        // saving the customer
        customerRepo.save(customer);
        // creating a saving account for the customer.
        savingsAccountService.addSavingsAccount(
                new SavingsAccount(customer.getDateBecameCustomer(), customer, 5000L)
        );
    }
}
