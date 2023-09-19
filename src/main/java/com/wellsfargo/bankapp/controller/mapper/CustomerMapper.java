package com.wellsfargo.bankapp.controller.mapper;

import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;

public class CustomerMapper {
    public static CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAge(customer.getAge());
        customerDTO.setGender(customer.getGender());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        customerDTO.setDateBecameCustomer(customer.getDateBecameCustomer());

        // You can omit the mapping for password and account lists

        return customerDTO;
    }
}