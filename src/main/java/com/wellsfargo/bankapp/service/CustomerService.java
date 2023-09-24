package com.wellsfargo.bankapp.service;

import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.exception.CustomerNotFoundException;
import com.wellsfargo.bankapp.mapper.CustomerDTOMapper;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerDTOMapper customerDTOMapper;
    @Autowired
    public CustomerService(CustomerRepo customerRepo, CustomerDTOMapper customerDTOMapper) {
        this.customerRepo = customerRepo;
        this.customerDTOMapper = customerDTOMapper;
    }

    public void registerCustomer(Customer customer){
        String email = customer.getEmail();
        Long mobileNumber = customer.getMobileNumber();
        Optional<Customer> customerByEmail = customerRepo.findCustomerByEmail(email);
        Optional<Customer> customerByMobileNumber = customerRepo.findCustomerByMobileNumber(mobileNumber);
        if(customerByEmail.isPresent()){
            throw new IllegalStateException("email id" + email + "taken");
        }
        if(customerByMobileNumber.isPresent()){
            throw new IllegalStateException("mobile number" + mobileNumber.toString() + "taken");
        }
        customerRepo.save(customer);
    }

    public CustomerDTO findCustomerById(Long id){
        return customerRepo.findById(id)
                .map(customerDTOMapper)
                .orElseThrow(() -> new CustomerNotFoundException("Customer by id " + id + " was not found."));
    }

    public Customer findCustomerByIdInternal(Long id){
        return customerRepo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer by id " + id + " was not found."));
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

    public List<CustomerDTO> findAllCustomers() {
        return customerRepo
                .findAll()
                .stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }
}
