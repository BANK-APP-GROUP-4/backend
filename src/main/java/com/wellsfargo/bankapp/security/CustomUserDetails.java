package com.wellsfargo.bankapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

	private String customUsername;
	private String customPassword;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public CustomUserDetails(String customUsername, String customPassword,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.customUsername = customUsername;
		this.customPassword = customPassword;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stubr
		
		return customPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return customUsername;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
