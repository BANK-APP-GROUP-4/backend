package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        transactionService.addTransaction(senderAccId, receiverAccId, amount);
        return ResponseEntity.status(HttpStatus.OK).body("Successful transaction.");
    }

    @ResponseBody
//    @GetMapping(path="/last/{k}/{id}")
    @RequestMapping(value = "/last", method = RequestMethod.POST, 
    headers = "Accept=application/json")
    public ResponseEntity<Map<String, Object>> getLastKTransactionsOfCustomer(@RequestBody String req) throws JsonMappingException, JsonProcessingException {
    	JsonNode reqNode = objectMapper.readTree(req);
    	int k = reqNode.get("k").asInt();
    	Long id = reqNode.get("id").asLong();
    	
        List<Transaction> history = transactionService.getLastKTransactions(id, k);
        
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "successful");
        res.put("transactions",  history);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @ResponseBody
//    @GetMapping(path="/statement/{months}/{id}")
    @RequestMapping(value = "/statement/{months}/{id}", method = RequestMethod.POST, 
    headers = "Accept=application/json")
    public ResponseEntity<List<Transaction>> getStatement(@PathVariable("id") Long id, @PathVariable("months") int m) {
        List<Transaction> list = transactionService.getStatement(id, m);
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

}
