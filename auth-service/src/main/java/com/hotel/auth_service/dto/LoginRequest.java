package com.hotel.auth_service.dto;

public class LoginRequest {

    private String identifier; // email OR phone
    private String password;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    

    // getters & setters
}
