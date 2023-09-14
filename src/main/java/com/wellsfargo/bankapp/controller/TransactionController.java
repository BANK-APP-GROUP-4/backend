package com.wellsfargo.bankapp.controller;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.service.CustomerService;
import com.wellsfargo.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(origins="http://localhost:3001")
    @PostMapping("/send")
    void sendAmount(
            @RequestParam(name="senderAccNumber") Long senderAccNumber,
            @RequestParam(name="receiverAccNumber") Long receiverAccNumber,
            @RequestParam(name="amount") Long amount){
        transactionService.addTransaction(senderAccNumber, receiverAccNumber, amount);
    }
}
