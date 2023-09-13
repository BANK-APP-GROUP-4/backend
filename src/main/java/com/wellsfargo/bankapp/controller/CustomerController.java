package com.wellsfargo.bankapp.controller;


import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/add")
    void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }
}
