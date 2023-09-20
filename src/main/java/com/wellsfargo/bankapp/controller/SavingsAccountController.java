package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/savings")
public class SavingsAccountController {

    @Autowired
    private final SavingsAccountService savingsAccountService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public SavingsAccountController(SavingsAccountService savingsAccountService) {
        this.savingsAccountService = savingsAccountService;
    }

    @PostMapping
    public ResponseEntity<String> createSavingsAccount(@RequestBody String createSavingsRequest) throws Exception {
        JsonNode createSavingsRequestNode = objectMapper.readTree(createSavingsRequest);
        Long customerId = createSavingsRequestNode.get("customerId").asLong();
        double depositAmount = createSavingsRequestNode.get("depositAmount").asDouble();
        Boolean hasCreditCard = createSavingsRequestNode.get("hasCreditCard").asBoolean();
        Boolean hasDebitCard = createSavingsRequestNode.get("hasDebitCard").asBoolean();

        savingsAccountService.createSavingsAccount(customerId, depositAmount, hasCreditCard, hasDebitCard);
        return new ResponseEntity<>("Savings account successfully created.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavingsAccountDTO> getSavingsAccountDetails(@PathVariable("id") Long id){
        SavingsAccountDTO savingsAccount = savingsAccountService.findSavingsAccountById(id);
        return new ResponseEntity<>(savingsAccount, HttpStatus.OK);
    }

}
