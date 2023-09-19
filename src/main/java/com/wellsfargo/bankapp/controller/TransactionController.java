package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.service.CustomerService;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import com.wellsfargo.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3001")
@RequestMapping("api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SavingsAccountService savingsAccountService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendAmount(@RequestBody String sendAmountRequest) throws Exception {
        JsonNode sendAmountRequestNode = objectMapper.readTree(sendAmountRequest);
        Long senderAccId = sendAmountRequestNode.get("senderAccId").asLong();
        Long receiverAccId = sendAmountRequestNode.get("receiverAccId").asLong();
        double amount = sendAmountRequestNode.get("amount").asDouble();
        String status = transactionService.addTransaction(senderAccId, receiverAccId, amount);
        if(status == "VALID"){
            return ResponseEntity.status(HttpStatus.OK).body("Successful transaction.");
        }
        else if(status == "NO BALANCE"){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Insufficient balance.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
        }

    }

    @ResponseBody
    @GetMapping(path="/history/{id}/{k}")
    public ResponseEntity<List<Transaction>> getLastKTransactionsOfCustomer(@PathVariable("id") String id, @PathVariable("k") String k) {
        if(customerService.isCustomerPresent(Long.valueOf(id))){
            List<Transaction> history = transactionService.getLastKTransactions(Long.valueOf(id), Integer.valueOf(k));
            return ResponseEntity.status(HttpStatus.OK).body(history);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
