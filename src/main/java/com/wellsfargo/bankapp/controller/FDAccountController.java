package com.wellsfargo.bankapp.controller;

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

    @PostMapping
    ResponseEntity<String> createFDAccount(@RequestBody Map<String, Object> jsonObj) throws Exception {
        Object customerId = jsonObj.get("customerId");
        Object principalAmount = jsonObj.get("principalAmount");
        Object maturityPeriod = jsonObj.get("maturityPeriod");
        fdAccountService.createFDAccount(new Long(customerId.toString()), new Double(principalAmount.toString()), new Integer(maturityPeriod.toString()));
        return new ResponseEntity<>("FD Account created successfully.", HttpStatus.OK);
    }
}
