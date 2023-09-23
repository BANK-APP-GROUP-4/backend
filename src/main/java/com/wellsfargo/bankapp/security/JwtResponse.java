package com.wellsfargo.bankapp.security;

import lombok.Builder;

@Builder
public class JwtResponse {
	private String tken;
	private String username;
	public static JwtResponseBuilder builder() {
		return new JwtResponseBuilder();
	}

	public static class JwtResponseBuilder{
		private JwtResponse instance = new JwtResponse();
	
	public JwtResponseBuilder tken(String tken) {
		instance.tken = tken;
		return this;
	}
	public JwtResponseBuilder username(String username) {
		instance.username = username;
		return this;
	}
	public JwtResponse build() {
		return instance;
	}
	
	
	}

	public String getTken() {
		return tken;
	}
	public void setTken(String tken) {
		this.tken = tken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public JwtResponse(String tken, String username) {
		super();
		this.tken = tken;
		this.username = username;
	}
	
	public JwtResponse() {}
	
}
