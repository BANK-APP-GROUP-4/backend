package com.wellsfargo.bankapp.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.mapper.CustomerDTOMapper;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.security.JwtHelper;
import com.wellsfargo.bankapp.security.JwtResponse;
import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
    private final CustomerService customerService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private JwtHelper helper;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        customerService.registerCustomer(customer);
        return new ResponseEntity<>("Customer successfully registered.", HttpStatus.OK);

    }

    @PostMapping("/login")
    public  ResponseEntity<JwtResponse> loginCustomer(@RequestBody String loginRequest) throws IOException {
        JsonNode loginRequestNode = objectMapper.readTree(loginRequest);
        
        String email = loginRequestNode.get("email").asText();
        String password = loginRequestNode.get("password").asText();
        Optional<Customer> cust = customerRepo.findCustomerByEmail(email);
        Customer customer = cust.get();
        String token = this.helper.generateToken(customer);

        JwtResponse response = JwtResponse.builder()
                .tken(token)
                .username(customer.getEmail()).build();
        Boolean isSuccessful = customerService.validateCustomer(email, password);
        if(isSuccessful){
            // todo: jwt Authentication
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, 
            headers = "Accept=application/json")
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerByIdInternal(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }
    
    //added for local storage in UI
    @RequestMapping(value = "/details/{email}", method = RequestMethod.POST, 
            headers = "Accept=application/json")
    public ResponseEntity<Customer> getCustomerDetailsByEmail(@PathVariable("email") String email){
        Customer customer = customerService.findCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }
    //added for local storage in UI
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.POST, 
            headers = "Accept=application/json")
    public ResponseEntity<List<SavingsAccount>> getCustomerAccountsById(@PathVariable("id") Long id){
        List<SavingsAccount> accounts = customerService.findCustomerAccountsById(id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);

    }
    //to change the password
    @PutMapping("/changePassword/{otp}")
	public String changePassword(@RequestBody String loginRequest, @PathVariable("otp") String otp) throws IOException {
    	 JsonNode loginRequestNode = objectMapper.readTree(loginRequest);
         
         String email = loginRequestNode.get("email").asText();
         String password = loginRequestNode.get("password").asText();
    	return customerService.changePassword(email,password, otp);
	}
    
    @RequestMapping(value = "/all", method = RequestMethod.POST, 
            headers = "Accept=application/json")
    public ResponseEntity<List<CustomerDTO>> getAllEmployees(){
        List<CustomerDTO> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
