package com.wellsfargo.bankapp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.wellsfargo.bankapp.service.CustomerService;
import com.wellsfargo.bankapp.service.SavingsAccountService;abstract

@RunWith(SpringRunner.class)
@WebMvcTest
public class SavingsAccountControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private SavingsAccountRepo savingsAccountRepo;
	
	@MockBean
	private SavingsAccountService savingsAccountService;
	
	@MockBean
	private CustomerService customerService;
	
	private static ObjectMapper mapper = new ObjectMapper();

	String jwt_token = "";
	Long customerId = 123456L;
    double depositAmount = 5000;
    Boolean hasCreditCard = true;
    Boolean hasDebitCard = false;

	
	@Test
	public void testCreateSavingsAccount() throws Exception {
		
//        Customer customer = customerService.findCustomerByIdInternal(customerId);
//
//        SavingsAccount savingsAccount = new SavingsAccount();
//        
//        savingsAccount.setHasCreditCard(hasCreditCard);
//        savingsAccount.setHasDebitCard(hasDebitCard);
//		LocalDateTime localdate = LocalDateTime.now();
//        savingsAccount.setActivationDate(localdate);
//        savingsAccount.setCustomer(customer);
//        savingsAccount.setBalance(5000);
//        savingsAccount.setAccountStatus(true);
		JSONObject obj = new JSONObject();
		obj.put("customerID", customerId);
		obj.put("depositAmount", depositAmount);
		obj.put("hasCreditCard",hasCreditCard );
		obj.put("hasDebitCard", hasDebitCard);
		String json = mapper.writeValueAsString(obj);

        
        mvc.perform(post("/api/v1/account/savings").header("Authorization", "Bearer " +jwt_token )
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	
	}
	
	@Test
	public void testGetSavingsAccountDetails() throws Exception {
        Customer customer = customerService.findCustomerByIdInternal(customerId);
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setHasCreditCard(hasCreditCard);
        savingsAccount.setHasDebitCard(hasDebitCard);
		LocalDateTime localdate = LocalDateTime.now();
        savingsAccount.setActivationDate(localdate);
        savingsAccount.setCustomer(customer);
        savingsAccount.setBalance(5000);     
        savingsAccount.setAccountStatus(true);
        
        List<SavingsAccount> savingsAccounts = new ArrayList<>();
        savingsAccounts.add(savingsAccount);
        JSONObject obj = new JSONObject();
        obj.put("customer_id", customerId);
		String json = mapper.writeValueAsString(obj);

        
		Mockito.when(savingsAccountService.findSavingsAccountByCustId(customerId)).thenReturn(savingsAccounts);
		 mvc.perform(post("/api/v1/account/savings/details").header("Authorization", "Bearer " +jwt_token )
					.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
					.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        
        
	}

}
