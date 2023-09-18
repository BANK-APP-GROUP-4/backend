package com.wellsfargo.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Login;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import com.wellsfargo.bankapp.validation.LoginValidation;

@RestController
@RequestMapping("/api/v1/customer")
public class LoginController {
	
	@Autowired
	private LoginValidation loginvalidation;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
	public JSONObject validateuser(@RequestBody Login login) {
		Customer customer = loginvalidation.validateLogin(login);
		JSONObject payload = new JSONObject();
		
		if(customer!=null) {
			SavingsAccount sa = savingsAccountService.findSavingsAccountById(customer.getId()).get();
			System.out.println(sa.getActivationDate());
			System.out.println(sa.getBalance());
			JSONObject obj = new JSONObject();
			obj.put("name", customer.getFirstName());
			obj.put("email", customer.getEmail());
			JSONObject obj2 = new JSONObject();
			obj2.put("Customer_id", sa.getId());
			obj2.put("Balance", sa.getBalance());
			obj2.put("Account_Activation_Date",sa.getActivationDate());
			payload.put("customer_details", obj);
			payload.put("account_details", obj2);
			payload.put("token", "12345");
			payload.put("status", "Success");
			payload.put("message","valid Creds");
			
			
		}
		else {
			payload.put("status", "failed");
			payload.put("message", "invalid credentials");
		}
		return payload;
	}
}
