package com.wellsfargo.bankapp.security;

public class JwtRequest {
	private Long customer_id;
	private String Password;
	public JwtRequest(Long customer_id, String password) {
		super();
		this.customer_id = customer_id;
		Password = password;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public JwtRequest() {}
	
}
