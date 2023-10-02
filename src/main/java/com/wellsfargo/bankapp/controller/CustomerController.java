package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.security.JwtHelper;
import com.wellsfargo.bankapp.security.JwtResponse;
import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.service.CustomerService;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@RestController
@RequestMapping(path="api/v1/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
    private final CustomerService customerService;
    private final SavingsAccountService savingsAccountService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private AuthenticationManager manager;
    private JwtHelper helper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(
            CustomerService customerService,
            SavingsAccountService savingsAccountService,
            AuthenticationManager manager,
            JwtHelper helper,
            PasswordEncoder passwordEncoder
    ) {
        this.customerService = customerService;
        this.savingsAccountService = savingsAccountService;
        this.manager = manager;
        this.helper = helper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody String req) throws Exception {
        JsonNode reqNode = objectMapper.readTree(req);

        JsonNode customerNode = reqNode.get("customer_details");
        JsonNode savingsAccountNode = reqNode.get("account_details");

        String encodedPassword = passwordEncoder.encode(customerNode.get("password").asText());

        Customer customer = new Customer(
                customerNode.get("firstName").asText(),
                customerNode.get("lastName").asText(),
                customerNode.get("address").asText(),
                customerNode.get("email").asText(),
                encodedPassword,
                customerNode.get("age").asInt(),
                customerNode.get("gender").asText(),
                customerNode.get("mobileNumber").asText(),
                LocalDate.now()
        );

        customerService.registerCustomer(customer);

        savingsAccountService.createSavingsAccount(
                customer,
                savingsAccountNode.get("depositAmount").asDouble(),
                savingsAccountNode.get("hasCreditCard").asBoolean(),
                savingsAccountNode.get("hasDebitCard").asBoolean()
        );

        return new ResponseEntity<>("Customer successfully registered.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public  ResponseEntity<JwtResponse> loginCustomer(@RequestBody String loginRequest) throws IOException {
        JsonNode loginRequestNode = objectMapper.readTree(loginRequest);

        String email = loginRequestNode.get("email").asText();
        String password = loginRequestNode.get("password").asText();
        Customer customer = customerService.findCustomerByEmail(email);
        String token = this.helper.generateToken(customer);

        Boolean isSuccessful = customerService.validateCustomer(email, password);
        JwtResponse response;
        if(isSuccessful){
            response = JwtResponse.builder()
                    .tken(token)
                    .username(customer.getEmail())
                    .status("success")
                    .message("Login Successful").build();
        }
        else{
            response = JwtResponse.builder()
                    .tken("no token")
                    .username("no username")
                    .status("failure")
                    .message("Invalid Credentials").build();
            //return new ResponseEntity<>(response2,HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value="{id}")
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerByIdInternal(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    //added for local storage in UI
    @PostMapping(value="/details")
    public ResponseEntity<Map<String,Object>> getCustomerDetailsByEmail(@RequestBody String request) throws IOException{
    	JsonNode loginRequestNode = objectMapper.readTree(request);

        String email = loginRequestNode.get("email").asText();
        Customer customer = customerService.findCustomerByEmail(email);
        Map<String, Object> details = new HashMap<>();
        details.put("customer_details", customer);
        details.put("status", "success");
        return ResponseEntity.status(HttpStatus.OK).body(details);

    }

    //to change the password
    @PutMapping("/changePassword/{otp}")
	public String changePassword(@RequestBody String loginRequest, @PathVariable("otp") String otp) throws IOException {
    	 JsonNode loginRequestNode = objectMapper.readTree(loginRequest);

         String email = loginRequestNode.get("email").asText();
         String password = loginRequestNode.get("password").asText();
    	return customerService.changePassword(email,password, otp);
	}

    @PostMapping(value="/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
