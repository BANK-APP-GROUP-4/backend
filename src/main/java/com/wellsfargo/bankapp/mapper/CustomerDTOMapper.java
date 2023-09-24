package com.wellsfargo.bankapp.mapper;

import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
    }
}