package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    void addCustomer(Customer customer){
        customerRepo.save(customer);
    }
}
