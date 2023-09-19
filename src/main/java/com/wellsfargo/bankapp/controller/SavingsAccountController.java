package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.service.CustomerService;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account/savings")
public class SavingsAccountController {

    @Autowired
    SavingsAccountService savingsAccountService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<String> createSavingsAccount(@RequestBody String createSavingsRequest) throws Exception {
        JsonNode createSavingsRequestNode = objectMapper.readTree(createSavingsRequest);
        Long customerId = createSavingsRequestNode.get("customerId").asLong();
        double depositAmount = createSavingsRequestNode.get("depositAmount").asDouble();
        int status = savingsAccountService.createSavingsAccount(customerId, depositAmount);
        if(status == 0){
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }
        else if(status == 1){
            return new ResponseEntity<>("Savings Account created successfully.", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Deposit amount has to be more than minimum balance.", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavingsAccount> getSavingsAccountDetails(@PathVariable("id") String id){
        Optional<SavingsAccount> savingsAccountByIdOp = savingsAccountService.findSavingsAccountById(Long.valueOf(id));
        if (!savingsAccountByIdOp.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        SavingsAccount savingsAccountById = savingsAccountByIdOp.get();
        return ResponseEntity.status(HttpStatus.OK).body(savingsAccountById);
    }

}
