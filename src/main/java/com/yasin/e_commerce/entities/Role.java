package com.yasin.e_commerce.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	ROLE_USER("USER"),
	ROLE_ADMIN("ADMIN"),
	ROLE_SELLER("SELLER");
	
	private String value;
	
	Role(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}

}