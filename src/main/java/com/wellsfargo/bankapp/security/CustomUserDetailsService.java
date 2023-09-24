package com.wellsfargo.bankapp.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wellsfargo.bankapp.entity.Customer;
import com.wellsfargo.bankapp.repository.CustomerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Customer> cus = customerRepo.findCustomerByEmail(username);
		Customer customer = cus.get();
		String customUsername = username;
		String customPassword = customer.getPassword();
		Collection<? extends GrantedAuthority> authorities = getAuthoritiesByUsername(username);
		
		
		return new CustomUserDetails(customUsername,customPassword,authorities);
	}
	private Collection<? extends GrantedAuthority> getAuthoritiesByUsername(String username){
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

}
