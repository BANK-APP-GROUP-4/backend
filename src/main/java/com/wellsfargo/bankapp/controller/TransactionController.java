package com.wellsfargo.bankapp.controller;

import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:3001")
@RequestMapping("api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    void sendAmount(@RequestBody Map<String, Object> jsonObj) throws Exception {
        Object senderAccId = jsonObj.get("senderAccId");
        Object receiverAccId = jsonObj.get("receiverAccId");
        Object amount = jsonObj.get("amount");
        transactionService.addTransaction(
                new Long(senderAccId.toString()),
                new Long(receiverAccId.toString()),
                new Double(amount.toString())
        );
    }

}
