package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.service.SavingsAccountService;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Map<String, Object>> getSavingsAccountDetails(@RequestBody String id) throws JsonMappingException, JsonProcessingException{
    	JsonNode loginRequestNode = objectMapper.readTree(id);
    	Long cust_id = loginRequestNode.get("customer_id").asLong();
        SavingsAccountDTO savingsAccount = savingsAccountService.findSavingsAccountByCustId(cust_id);
	    Map<String, Object> response = new HashMap<>();
	    response.put("savings_account_details", savingsAccount);
	    response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}