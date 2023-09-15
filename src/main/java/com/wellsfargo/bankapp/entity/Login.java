package com.wellsfargo.bankapp.entity;

public class Login {

	private Long username;
	private String password;
	public Login(Long username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Long getUsername() {
		return username;
	}
	public void setUsername(Long username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
