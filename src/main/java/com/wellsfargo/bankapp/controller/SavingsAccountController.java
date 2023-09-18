package com.wellsfargo.bankapp.controller;

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

    @PostMapping
    public ResponseEntity<String> createSavingsAccount(@RequestBody Map<String, Object> jsonObj) throws Exception {
        Object customerId = jsonObj.get("customerId");
        Object depositAmount = jsonObj.get("depositAmount");
        savingsAccountService.createSavingsAccount(new Long(customerId.toString()), new Double(depositAmount.toString()));
        return new ResponseEntity<>("Savings Account created successfully.", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path="{id}")
    public List<Transaction> getTransactionHistory(@PathVariable("id") String id) throws Exception {
        Optional<SavingsAccount> savingsAccountOp = savingsAccountService.findSavingsAccountById(new Long(id));
        if(savingsAccountOp.isPresent()){
            SavingsAccount savingsAccount = savingsAccountOp.get();
            List<Transaction> history = savingsAccount.getCreditTransactions();
            return history;
        }
        else{
            throw new Exception("Account not found.");
        }
    }

}
