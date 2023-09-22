package com.wellsfargo.bankapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Admin {
	 @Id
	 private String email;
	 private String password;
	public String getEmail() {
		return email;
	}
	public void setUserid(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
