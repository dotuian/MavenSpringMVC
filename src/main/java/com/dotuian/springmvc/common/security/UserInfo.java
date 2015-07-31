package com.dotuian.springmvc.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserInfo extends User {

	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public UserInfo(String username, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities)
			throws IllegalArgumentException {
		
		super(username, password, true, true, true, true, authorities);
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	
}
