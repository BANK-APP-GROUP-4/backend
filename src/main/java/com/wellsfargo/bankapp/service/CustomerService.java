package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public void registerCustomer(Customer customer){
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
    }

    public Optional<Customer> findCustomerById(Long id){
        return customerRepo.findById(id);
    }

    public Boolean isCustomerPresent(Long id){
        if(findCustomerById(id).isPresent()){
            return true;
        }
        return false;
    }

    public Boolean validateCustomer(String email, String password) {
        Optional<Customer> customerByEmailOp = customerRepo.findCustomerByEmail(email);
        if(customerByEmailOp.isPresent()){
            Customer customerByEmail = customerByEmailOp.get();
            if(customerByEmail.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}
