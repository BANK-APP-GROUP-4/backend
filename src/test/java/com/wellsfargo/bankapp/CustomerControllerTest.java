package com.wellsfargo.bankapp;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.bankapp.dto.CustomerDTO;
import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.repository.CustomerRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.wellsfargo.bankapp.service.CustomerService;abstract


@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerRepo customerRepo;

	@MockBean
	private CustomerService customerService;
	private static ObjectMapper mapper = new ObjectMapper();

	String jwt_token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJocml0aWNrQGdtYWlsLmNvbSIsImlhdCI6MTY5NTk1OTc3MSwiZXhwIjoxNjk2MDQ2MTcxfQ.n1qEZ8RkzLE0j7GuV7KjdHNGW8m3y0eH-maq38t11NSZwbC0OvSaafvWh23bdz2Jp7UeeKuEiFI3ZTaQdLqLOQ";
	
	@Test
	public void testRegisterCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setFirstName("Sambath");
		customer.setLastName("Kumar");
		customer.setAddress("Hosur");
		LocalDateTime localdate = LocalDateTime.now();
		customer.setDateBecameCustomer(localdate);
		customer.setAge(22);
		customer.setGender("male");
		customer.setMobileNumber(9876543210L);
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("hello123");
		String json = mapper.writeValueAsString(customer);
		mvc.perform(post("/api/v1/customer/register")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	
		
	}
	
	
	@Test
	public void testGetCustomerDetails() throws Exception {
		Customer customer = new Customer();
		customer.setId(123456L);
		customer.setFirstName("Sambath");
		customer.setLastName("Kumar");
		customer.setAddress("Hosur");
		LocalDateTime localdate = LocalDateTime.now();
		customer.setDateBecameCustomer(localdate);
		customer.setAge(22);
		customer.setGender("male");
		customer.setMobileNumber(9876543210L);
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("hello123");
		String json = mapper.writeValueAsString(customer);
		mvc.perform(post("/api/v1/customer/{id}",123456L).header("Authorization", "Bearer " +jwt_token )
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	
	}
	
	@Test
	public void testGetCustomerDetailsByEmail() throws Exception {
		Customer customer = new Customer();
		customer.setId(123456L);
		customer.setFirstName("Sambath");
		customer.setLastName("Kumar");
		customer.setAddress("Hosur");
		LocalDateTime localdate = LocalDateTime.now();
		customer.setDateBecameCustomer(localdate);
		customer.setAge(22);
		customer.setGender("male");
		customer.setMobileNumber(9876543210L);
		customer.setEmail("abcd@gmail.com");
		customer.setPassword("hello123");
		Mockito.when(customerService.findCustomerByIdInternal(ArgumentMatchers.anyLong())).thenReturn(customer);
		mvc.perform(post("/api/v1/customer/details").header("Authorization", "Bearer " +jwt_token )
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk()).
			      andExpect(jsonPath("$.firstName", Matchers.equalTo(customer.getFirstName())));
	
	
	}
	
	@Test
	public void testChangePassword() throws Exception {
		String otp = "1111";
		String email = "abcd@gmail.com";
		String password = "hello123";
		String result = "success";
		Mockito.when(customerService.changePassword(email,password,otp)).thenReturn(result);
		mvc.perform(put("/changePassword/{otp}",otp).header("Authorization", "Bearer " +jwt_token )
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());

		
	}
	
	@Test
	public void testGetAllCustomers() throws Exception {
        List<CustomerDTO> customers = customerService.findAllCustomers();
		Mockito.when(customerService.findAllCustomers()).thenReturn(customers);
		mvc.perform(post("/all").header("Authorization", "Bearer " +jwt_token )
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
}
