package com.wellsfargo.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.bankapp.entity.Admin;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Transaction;
import com.wellsfargo.bankapp.entity.UpdateRequest;
import com.wellsfargo.bankapp.service.AdminService;
import com.wellsfargo.bankapp.service.CustomerService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
    private AdminService adminService;
    
    @CrossOrigin(origins="http://localhost:3001")
    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody Admin admin){
       String s=adminService.saveAdmin(admin);
    	return  ResponseEntity.ok(s);
    }
	
    @PostMapping("/adminLogin")
	public ResponseEntity<String> adminLogin(@RequestBody Admin admin)
	{
    	String s=adminService.adminLogin(admin);
    	return  ResponseEntity.ok(s);
	}
    
    @PostMapping("/disable/{accno}")
	public ResponseEntity<String> disableAccount(@PathVariable("accno") long accno)
	{
    	String s=adminService.disableAccount(accno);
	
	return  ResponseEntity.ok(s);
	}
    
    @PostMapping("/activate/{accno}")
	public ResponseEntity<String> activateAccount(@PathVariable("accno") long accno)
	{
    	String s=adminService.activateAccount(accno);
    	return  ResponseEntity.ok(s);
	}
	
    @GetMapping("/getAllCustomers")
	public List<Customer> getCustomers() 
	{
		return adminService.getCustomers();
		
	}
    
    @PostMapping("/update")
	public ResponseEntity<String> updateBalance(@RequestBody UpdateRequest updateRequest) 
	{
    	Long accNumber=updateRequest.getAccNumber();
    	Long amount=updateRequest.getAmount();
    	String s=adminService.updateBalance(accNumber,amount);
    	return  ResponseEntity.ok(s);
		
	}
	
//    @GetMapping("/getAllTransactions")
//	public List<Transaction> getTransactions()
//	{
//		return adminService.getTransactions();
//	}
	
}
