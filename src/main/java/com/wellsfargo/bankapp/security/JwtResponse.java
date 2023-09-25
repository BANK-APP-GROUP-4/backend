package com.wellsfargo.bankapp.security;

import lombok.Builder;

@Builder
public class JwtResponse {
	private String tken;
	private String username;
	private String status;
	private String message;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	public JwtResponseBuilder status(String status) {
		instance.status = status;
		return this;
	}
	public JwtResponseBuilder message(String message) {
		instance.message = message;
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

	public JwtResponse(String tken, String username, String status, String message) {
		super();
		this.tken = tken;
		this.username = username;
		this.status = status;
		this.message = message;
	}
	public JwtResponse() {}
	
}
