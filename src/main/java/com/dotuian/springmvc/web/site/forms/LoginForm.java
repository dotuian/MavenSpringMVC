package com.dotuian.springmvc.web.site.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

	@Size(min=3, max=20)
	@NotNull
	private String username;
	@NotNull
	@Size(min=6, max=15)
	private String password;
	
	private boolean rememberMe = false;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the rememberMe
	 */
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe
	 *            the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
