package com.wellsfargo.bankapp.controller;

import com.wellsfargo.bankapp.entity.Account;
import com.wellsfargo.bankapp.entity.FDAccount;
import com.wellsfargo.bankapp.entity.SavingsAccount;
import com.wellsfargo.bankapp.service.FDAccountService;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    SavingsAccountService savingsAccountService;

    @Autowired
    FDAccountService fdAccountService;
    @CrossOrigin(origins="http://localhost:3001")
    @GetMapping("/all/{customerId}")
    List<Long> getAllAccounts(@PathVariable("customerId") Long customerId){
        List<SavingsAccount> savingsAccountList = savingsAccountService.findAllSavingsAccount(customerId);
        List<FDAccount> fdAccountList = fdAccountService.findAllFdAccount(customerId);

        List<Long> savingsAccNumberList = savingsAccountList.stream()
                .map((SavingsAccount sa) -> sa.getId())
                .collect(Collectors.toList());

        List<Long> fdAccountNumberList = fdAccountList.stream()
                .map((FDAccount fda) -> fda.getId())
                .collect(Collectors.toList());

        List<Long> accountNumberList = Stream.concat(savingsAccNumberList.stream(), fdAccountNumberList.stream())
                .collect(Collectors.toList());

        return accountNumberList;

    }
}
