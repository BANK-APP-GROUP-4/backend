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
import com.wellsfargo.bankapp.dto.FDAccountDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.account.SavingsAccount;
import com.wellsfargo.bankapp.repository.CustomerRepo;
import com.wellsfargo.bankapp.repository.FDAccountRepo;
import com.wellsfargo.bankapp.repository.SavingsAccountRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.wellsfargo.bankapp.service.CustomerService;
import com.wellsfargo.bankapp.service.FDAccountService;
import com.wellsfargo.bankapp.service.SavingsAccountService;abstract

@RunWith(SpringRunner.class)
@WebMvcTest
public class FDAccountControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private FDAccountRepo fdAccountRepo;
	
	@MockBean
	private FDAccountService fdAccountService;
	
	private static ObjectMapper mapper = new ObjectMapper();

	String jwt_token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJocml0aWNrQGdtYWlsLmNvbSIsImlhdCI6MTY5NTk1OTc3MSwiZXhwIjoxNjk2MDQ2MTcxfQ.n1qEZ8RkzLE0j7GuV7KjdHNGW8m3y0eH-maq38t11NSZwbC0OvSaafvWh23bdz2Jp7UeeKuEiFI3ZTaQdLqLOQ";
	Long customerId = 123456L;
    double principalAmount = 5000;
    int maturityPeriod = 4;
	
	@Test
	public void testCreateFDAccount() throws Exception {
		    
	        
	        JSONObject obj = new JSONObject();
	        obj.put("customerId",customerId);
	        obj.put("principalAmount", 123456L);
	        obj.put(maturityPeriod, 4);
			String json = mapper.writeValueAsString(obj);
	        mvc.perform(post("/api/v1/account/fixed").header("Authorization", "Bearer " +jwt_token )
					.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
					.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetFDAccountDetails() throws Exception {
		FDAccountDTO fdAccountdto = new FDAccountDTO();
		fdAccountdto.setId(customerId);
		fdAccountdto.setMaturityPeriod(maturityPeriod);
		fdAccountdto.setPrincipalAmount(principalAmount);
		Mockito.when(fdAccountService.findSavingsAccountById(customerId)).thenReturn(fdAccountdto);
		mvc.perform(post("/api/v1/customer/fixed/{id}",customerId).header("Authorization", "Bearer " +jwt_token )
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	
	@Test
	public void testGetFDAccountsDetails() throws Exception {
		
		List<FDAccountDTO> FDs = new ArrayList<>();
		FDAccountDTO fdAccountdto = new FDAccountDTO();
		fdAccountdto.setId(customerId);
		fdAccountdto.setMaturityPeriod(maturityPeriod);
		fdAccountdto.setPrincipalAmount(principalAmount);
		FDs.add(fdAccountdto);
		Mockito.when(fdAccountService.findFDAccountsByCustId(customerId)).thenReturn(FDs);
		mvc.perform(post("/api/v1/customer/fixed/details").header("Authorization", "Bearer " +jwt_token )
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
}
