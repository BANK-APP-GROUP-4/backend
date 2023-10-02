package com.wellsfargo.bankapp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<Map<String, String>> sendAmount(@RequestBody String sendAmountRequest) throws Exception {
        JsonNode sendAmountRequestNode = objectMapper.readTree(sendAmountRequest);
        Long senderAccId = sendAmountRequestNode.get("senderAccId").asLong();
        Long receiverAccId = sendAmountRequestNode.get("receiverAccId").asLong();
        double amount = sendAmountRequestNode.get("amount").asDouble();
        //transactionService.addTransaction(senderAccId, receiverAccId, amount);
        String message =transactionService.addTransaction(senderAccId, receiverAccId, amount);
        Map<String,String> s=new HashMap<>();
        s.put("message",message);

        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @PostMapping(value="/last")
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

    @PostMapping(value="/summary")
    public ResponseEntity<Map<String,Object>> getStatement(@RequestBody String req) throws JsonProcessingException {
        JsonNode reqNode = objectMapper.readTree(req);

        Long id = reqNode.get("id").asLong();
        String fromDate = reqNode.get("from").toString();
        String toDate = reqNode.get("to").toString();

        System.out.println(fromDate);
        System.out.println(toDate);

        System.out.println(fromDate.replaceAll("\"", ""));
        System.out.println(toDate.replaceAll("\"", ""));

        LocalDate from = LocalDate.parse(fromDate.replaceAll("\"", ""));
        LocalDate to = LocalDate.parse(toDate.replaceAll("\"", ""));

        List<Transaction> list = transactionService.getStatement(id, from, to);
        Map<String,Object> response=new HashMap<>();
        response.put("message","successful");
        response.put("status","success");
        response.put("transaction", list);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
