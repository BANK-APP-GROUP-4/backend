package com.wellsfargo.bankapp.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.entity.Login;
import com.wellsfargo.bankapp.repository.CustomerRepo;

@Service
public class LoginValidation {

	@Autowired
	private CustomerRepo customerRepo;
	

	public Customer validateLogin(Login user) {
		Customer cus = null;
		Optional<Customer> customer = customerRepo.findCustomerByfname(user.getUsername());
		if(customer.isPresent()) {
			cus = customer.get();


		}
		if(cus == null) {
			System.out.println("Invalid User");
		}
		else {
			if(user.getPassword().equals(cus.getPassword())) {
				System.out.println("valid user");
				
			}
			else {

				 cus = null;
				 return cus;

//				 throw new IllegalStateException("invalid Login");	
				 }
		}
		
		return cus;
	}
	
}
