package com.wellsfargo.bankapp.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.controller.mapper.CustomerMapper;
import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins="http://localhost:3001")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        customerService.registerCustomer(customer);
        return new ResponseEntity<>("Customer successfully registered.", HttpStatus.OK);

    }

    @PostMapping("/login")
    public  ResponseEntity<String> loginCustomer(@RequestBody String loginRequest) throws IOException {
        JsonNode loginRequestNode = objectMapper.readTree(loginRequest);

        String email = loginRequestNode.get("email").asText();
        String password = loginRequestNode.get("password").asText();

        Boolean isSuccessful = customerService.validateCustomer(email, password);
        if(isSuccessful){
            // todo: jwt Authentication
            return new ResponseEntity<>("Logged in successfully.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Login unsuccessful", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable("id") String id){
        Optional<Customer> customerByIdOp = customerService.findCustomerById(Long.valueOf(id));
        if (!customerByIdOp.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        CustomerDTO customer = CustomerMapper.mapToDTO(customerByIdOp.get());
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
}
