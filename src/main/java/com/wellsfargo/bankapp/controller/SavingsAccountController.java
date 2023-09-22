//package com.wellsfargo.bankapp.controller;
//
//import com.wellsfargo.bankapp.entity.Transaction;
//import com.wellsfargo.bankapp.entity.account.SavingsAccount;
//import com.wellsfargo.bankapp.service.CustomerService;
//import com.wellsfargo.bankapp.service.SavingsAccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.time.LocalDateTime;
//
//@RestController
//@RequestMapping("/api/v1/account/savings")
//public class SavingsAccountController {
//
//    @Autowired
//    SavingsAccountService savingsAccountService;
//
//    @PostMapping
//    public ResponseEntity<String> createSavingsAccount(@RequestBody Map<String, Object> jsonObj) throws Exception {
//        Object customerId = jsonObj.get("customerId");
//        Object depositAmount = jsonObj.get("depositAmount");
//        savingsAccountService.createSavingsAccount(new Long(customerId.toString()), new Double(depositAmount.toString()));
//        return new ResponseEntity<>("Savings Account created successfully.", HttpStatus.OK);
//    }
//
//    
//    @GetMapping(path="/id")
//    public Integer getTransactionHistory()  {
//    	//return savingsAccountService.getTransactionHistory(id);
//    	return 5;
//    	
//    	
////        Optional<SavingsAccount> savingsAccountOp = savingsAccountService.findSavingsAccountById(id);
////        if(savingsAccountOp.isPresent()){
////            SavingsAccount savingsAccount = savingsAccountOp.get();
////            List<Transaction> credTransactions = savingsAccount.getCreditTransactions();
////            List<Transaction> debTransactions = savingsAccount.getDebitTransactions();
////            Collections.reverse(debTransactions);
////            Collections.reverse(credTransactions);
////
////            List<Transaction> history = new ArrayList<>();
////            Integer i1=0;
////            Integer i2=0;
////            Integer count=0;
////            while(i1<credTransactions.size() && i2<debTransactions.size()&& count<10) {
////            	LocalDateTime dateTime1=credTransactions.get(i1).getDateOfTransaction();
////    			LocalDateTime dateTime2=debTransactions.get(i2).getDateOfTransaction();
////    			if(dateTime1.isAfter(dateTime2)) {
////    				history.add(credTransactions.get(i1));
////    				i1=i1+1;
////    				count=count+1;
////    			}else {
////    				history.add(debTransactions.get(i2));
////    				i2=i2+1;
////    				count=count+1;
////    			}
////            }
////            
////
////            return history;
////        }
////        else{
////            throw new Exception("Account not found.");
////        }
//    }
//
//}

package com.wellsfargo.bankapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.bankapp.service.SavingsAccountService;
import com.wellsfargo.bankapp.entity.Transaction;


@RestController
@RequestMapping("api/v1/savings")
public class SavingsAccountController {
	
	
	@Autowired
	SavingsAccountService savingsAccountService;
	
	 @GetMapping(path="/{id}")
   public List<Transaction> getTransactionHistory(@PathVariable("id") Long id)  {
		 
		 return savingsAccountService.getTransactionHistory(id);
	 }
	
}

