package com.wellsfargo.bankapp.controller;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.dto.FDAccountDTO;
import com.wellsfargo.bankapp.dto.SavingsAccountDTO;
import com.wellsfargo.bankapp.service.FDAccountService;

import antlr.collections.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/account/fixed")
public class FDAccountController {

    @Autowired
    FDAccountService fdAccountService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping
    ResponseEntity<String> createFDAccount(@RequestBody String createFDRequest) throws Exception {

        JsonNode createFDRequestNode = objectMapper.readTree(createFDRequest);

        Long customerId = createFDRequestNode.get("customerId").asLong();
        double principalAmount = createFDRequestNode.get("principalAmount").asDouble();
        int maturityPeriod = createFDRequestNode.get("maturityPeriod").asInt();

        fdAccountService.createFDAccount(customerId, principalAmount, maturityPeriod);
        return new ResponseEntity<>("FD Account created successfully.", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, 
            headers = "Accept=application/json")
    public ResponseEntity<FDAccountDTO> getFDAccountDetails(@PathVariable("id") Long id){
        FDAccountDTO fdAccount = fdAccountService.findSavingsAccountById(id);
        return new ResponseEntity<>(fdAccount, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Map<String, Object>> getFDAccountDetails(@RequestBody String id) throws JsonMappingException, JsonProcessingException{
    	JsonNode loginRequestNode = objectMapper.readTree(id);
    	Long cust_id = loginRequestNode.get("customer_id").asLong();
        List<FDAccountDTO> fdAccounts = fdAccountService.findFDAccountsByCustId(cust_id);
	    Map<String, Object> response = new HashMap<>();
	    response.put("fd_account_details", fdAccounts);
	    response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
