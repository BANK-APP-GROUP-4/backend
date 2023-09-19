package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.service.FDAccountService;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
