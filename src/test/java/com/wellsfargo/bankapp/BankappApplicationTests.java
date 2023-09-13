package com.wellsfargo.bankapp;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Login;
import com.wellsfargo.bankapp.entity.SavingsAccount;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;
import com.wellsfargo.bankapp.service.SavingsAccountService;
import com.wellsfargo.bankapp.validation.LoginValidation;

@SpringBootTest
class BankappApplicationTests {
	LocalDate ldate = LocalDate.now();

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired 
	private SavingsAccountRepo savingsAccountRepo;
	
	@Autowired
	private LoginValidation loginvalidation;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer();
		customer.setId(20L);
		customer.setFirstName("Sharanprasath");
		customer.setLastName("S");
		customer.setAddress("banglore");
		customer.setPassword("hello");
		customer.setEmail("prasath@gmail.com");
		customer.setAge(22);
		customer.setGender("male");
		customer.setMobileNumber(7L);
		customer.setDateBecameCustomer(ldate);
		customerRepo.save(customer);
		assertNotNull(customerRepo.findById(3L).get());	
		assertNotNull(savingsAccountRepo.findById(3L).get());
		
		
	}
	
	
	
	@Test
	public void testPayloadFrontend() {
		Login login1 = new Login();
		login1.setUsername(3L);
		login1.setPassword("hello");
		
		Customer customer = loginvalidation.validateLogin(login1);
		JSONObject payload = new JSONObject();
		JSONObject payload1 = new JSONObject();


		if(customer!=null) {
			JSONObject obj = new JSONObject();
			obj.put("name", "Sharanprasath");
			obj.put("email", "sharanprasath@gmail.com");
			JSONObject obj2 = new JSONObject();
			obj2.put("Customer_id", 3L);
			obj2.put("Balance", 5000L);
			obj2.put("Account_Activation_Date",LocalDate.of(2023, 9, 12));
			payload.put("customer_details", obj);
			payload.put("account_details", obj2);
			payload.put("token", "12345");
			payload.put("status", "Success");
			payload.put("message","valid Creds");
			
			SavingsAccount sa = savingsAccountService.savingsAccount(customer.getId());

			
			JSONObject obj3 = new JSONObject();
			obj3.put("name", customer.getFirstName());
			obj3.put("email", customer.getEmail());
			JSONObject obj4 = new JSONObject();
			obj4.put("Customer_id", sa.getId());
			obj4.put("Balance", sa.getBalance());
			obj4.put("Account_Activation_Date",sa.getActivationDate());
			payload1.put("customer_details", obj3);
			payload1.put("account_details", obj4);
			payload1.put("token", "12345");
			payload1.put("status", "Success");
			payload1.put("message","valid Creds");
			
			
			assertEquals(payload,payload1);
		}
		
		
		
		
			

	}
	
	
	@Test
	public void testLoginCreds() {
		Login login = new Login();
		login.setUsername(3L);
		login.setPassword("hello");
		
		if(customerRepo.findById(login.getUsername()).isPresent()) {
			assertEquals(customerRepo.findById(login.getUsername()).get().getPassword(),login.getPassword());
		}
	}
	
	
	
	
	
	
//	@Test
//	public void testSaveAccount() {
//			SavingsAccount sa = new SavingsAccount();
//			sa.setId(1L);
//			sa.setBalance(5000L);
//			sa.setActivationDate(ldate);
//			sa.se
//	}
//	
//	}

}


